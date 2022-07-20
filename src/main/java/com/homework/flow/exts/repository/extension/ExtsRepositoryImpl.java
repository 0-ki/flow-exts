package com.homework.flow.exts.repository.extension;

import com.homework.flow.exts.domain.Extension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ExtsRepositoryImpl implements ExtsRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public ExtsRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("extension")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Extension save(Extension extension) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(extension);
        Number key = jdbcInsert.executeAndReturnKey(param);
        extension.setId(key.longValue());
        return extension;
    }

    @Override
    public int update(Extension extensionDTO) {
        String sql = "update extension set name = :name,  flag_use= :flagUse, flag_fixed = :flagFixed" +
                " where id = :id";

        SqlParameterSource param = new BeanPropertySqlParameterSource(extensionDTO);
        return jdbcTemplate.update(sql, param);
    }

    @Override
    public Optional<Extension> findById(Long id) {
        String sql = "select id, name, flag_use, flag_fixed from extension where id = :id";
        try {
            Map<String, Object> param = Map.of("id", id);
            Extension extension = jdbcTemplate.queryForObject(sql, param, extensionRowMapper());
            return Optional.of(extension);

        } catch (EmptyResultDataAccessException e) {
            e.getMessage();
            return Optional.empty();
        }
    }

    @Override
    public List<Extension> findAll() {
        String sql = " select id, name, flag_use, flag_fixed from extension ";
        return jdbcTemplate.query(sql, extensionRowMapper());
    }

    @Override
    public int delete(Long id) {
        String sql = " delete from extension where id = :id ";
        Map<String, Object> param = Map.of("id", id);
        return jdbcTemplate.update(sql, param);
    }

    @Override
    public int getCountCustom() {
        String sql = " select count(*) from extension where flag_fixed = 0 ";
        Map<String, Object> param = Map.of();
        return jdbcTemplate.queryForObject(sql, param, Integer.class);
    }

    @Override
    public Optional<Extension> findByName(String name) {
        String sql = " select id from extension where name = :name ";
        Map<String, Object> param = Map.of("name", name);

        try{
            Extension extension = jdbcTemplate.queryForObject(sql, param, extensionRowMapper());
            return Optional.of(extension);
        } catch (EmptyResultDataAccessException e) {
            e.getMessage();
            return Optional.empty();
        }
    }

    private RowMapper<Extension> extensionRowMapper() {
        return BeanPropertyRowMapper.newInstance(Extension.class);
    }
}