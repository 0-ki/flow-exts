$(document).ready(function () {
    getAllExtensions();
    $("#customAdd").click( addCustomExts);
})

let getAllExtensions = function () {
    let datas = _AJAX({"type":"GET", "url":"/exts" });
	
    renderFixedExts(datas.filter(ext => ext.flagFixed));
	renderCustomExts(datas.filter(ext => !(ext.flagFixed)));
}

let renderFixedExts = function (datas) {
	let target = $("#form-extension").find("#fixed-area");
    target.empty();
	$.each(datas, function(idx, ext){
		let target_id = "fixed-" + ext.name;
		let fixedHtml =
            `<div class="fixed-div">
                <input type="checkbox" name="${ext.name}" flagFixed=${ext.flagFixed} id="${target_id}" oid="${ext.id}" ${ext.flagUse ? "checked":""}>
                <label for="${target_id}">${ext.name}</label>
            </div>`;
		
		$(fixedHtml).appendTo(target);
		$('#' + target_id).click( updateUseStatus);
	});// each
}

let renderCustomExts = function (datas) {
	let target = $("#custom-area");
    
	$.each(datas, function(idx, customExt){
		let target_id = "custom-" + customExt.name;
		let fixedHtml =
	    `<div class="custom-div" name="${customExt.name}" flagFixed=${customExt.flagFixed} id="${target_id}" oid="${customExt.id}" flagUse="${customExt.flagUse}" style="margin-right: 10px;padding: 2px;border: 1px solid grey;border-radius: 10px;">
		    ${customExt.name}<span>×</span>
	    </div>`;
		
		$(fixedHtml).appendTo(target);
		$('#' + target_id+ "> span").click( deleteCustomExt);
	});// each
	refreshCustomCount();
}

let addCustomExts = function (e) {
	if(e) {
		e.preventDefault();
	} else {
		return false; 
	}
	
	let target = $("#customInput");
	let ext = target.val();
	if (ext=='') return false;
	target.val('');	
	createCustomExt(ext.toUpperCase());
	refreshCustomCount();
	return false;
}

let createCustomExt = function (t_ext){
	let customExt = _AJAX({"type":"POST","url":"/exts/" + t_ext});
	renderCustomExts({customExt});

	return false;
}

let updateUseStatus = function (e) {
	let t_data = $(e.target);
	let extensionDto = new Object();
	extensionDto.id= parseInt(t_data.attr("oid"));
	extensionDto.name= t_data.attr("name");
	extensionDto.flagFixed= t_data.attr("flagFixed") == 'true' ? true : false;
	extensionDto.flagUse= e.target.checked;
	// extensionDto = JSON.stringify(extensionDto);
	let isSuccess = _AJAX({"type":"PUT", "url":"/exts/" + t_data.attr("oid"), "data": JSON.stringify(extensionDto) });
}

let deleteCustomExt = function(e) {
	e.preventDefault();
	let target = $(e.target).parent();
	
	let isSuccess = _AJAX({"type":"DELETE", "url":"/exts/" + parseInt(target.attr("oid"))});
	$(target).remove();
	refreshCustomCount();
}

let _AJAX = function (params) {
	let responseData;
	let _exception;
    $.get({
		type: params.type,
		url: params.url,
		data: params.data,
		async: false,
        contentType:'application/json;charset=utf-8',
		beforeSend: function() {
		},
		success: function(res) {
			responseData = res;
		},
		error: function (request, status, error) {
			console.log("code: " + request.status)
			console.log("msg: " + request.responseText)
			console.log("error: " + error);
			alert(request.responseText);
			throw request.status;
		}
    })
	if(responseData) return responseData;
}

let refreshCustomCount = function () {
	let customDom = $("#custom-area").find(".custom-div").length;
	$("#customCnt").text( customDom);
}
