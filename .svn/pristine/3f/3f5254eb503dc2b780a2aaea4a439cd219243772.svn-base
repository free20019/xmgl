 var basePath = "http://"+self.location.host+"/xmgl/";
 
//bootstrap-datetimepicker default option
var dateDefaultOption = {
	language:  'zh-CN',
	format: 'yyyy-mm-dd',
	autoclose: 1,
	startView: 2,
	minView: 2
};
// 瀵笵ate鐨勬墿灞曪紝灏�Date 杞寲涓烘寚瀹氭牸寮忕殑String
// 鏈�M)銆佹棩(d)銆佸皬鏃�h)銆佸垎(m)銆佺(s)銆佸搴�q) 鍙互鐢�1-2 涓崰浣嶇锛�
// 骞�y)鍙互鐢�1-4 涓崰浣嶇锛屾绉�S)鍙兘鐢�1 涓崰浣嶇(鏄�1-3 浣嶇殑鏁板瓧)
// 渚嬪瓙锛�
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.format = function(fmt)
{ //author: meizz
	var o = {
		"M+" : this.getMonth()+1,                 //鏈堜唤
		"d+" : this.getDate(),                    //鏃�
		"h+" : this.getHours(),                   //灏忔椂
		"m+" : this.getMinutes(),                 //鍒�
		"s+" : this.getSeconds(),                 //绉�
		"q+" : Math.floor((this.getMonth()+3)/3), //瀛ｅ害
		"S"  : this.getMilliseconds()             //姣
	};
	if(/(y+)/.test(fmt))
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	for(var k in o)
		if(new RegExp("("+ k +")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	return fmt;
};
function setformat(obj) {
	if(obj.length==0){
		return "";
	}
	var y = (obj.getFullYear()).toString();
	var m = (obj.getMonth() + 1).toString();
	if (m.length == 1) {
		m = "0" + m;
	}
	var d = obj.getDate().toString();
	if (d.length == 1) {
		d = "0" + d;
	}
	var time = y + "-" + m + "-" + d;
	return time;
}
function setformat1(obj) {
	if(obj.length==0){
		return "";
	}
	var y = (obj.getFullYear()).toString();
	var m = (obj.getMonth() + 1).toString();
	if (m.length == 1) {
		m = "0" + m;
	}
	var d = obj.getDate().toString();
	if (d.length == 1) {
		d = "0" + d;
	}
	var h = obj.getHours().toString();
	if (h.length == 1) {
		h = "0" + h;
	}
	var mi = obj.getMinutes().toString();
	if (mi.length == 1) {
		mi = "0" + mi;
	}
	var s = obj.getSeconds().toString();
	if (s.length == 1) {
		s = "0" + s;
	}
	var time = y + "-" + m + "-" + d + " " + h + ":" + mi + ":" + s;
	return time;
}
function comboboxDFun (id) {
	if (!id) {console.error('combobox鏂规硶闇�浼犲叆id鍊�');return false}
	$(id + ' .iFComboBox').off('click').on('click', function () {
		if (event.stopPropagation){event.stopPropagation();}else if (window.event) {window.event.cancelBubble = true;}
		var thisOne = this;
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			$(this).addClass('selected');
			$(this).find('.iFList').on('click', function () {
				if (event.stopPropagation){event.stopPropagation();}else if (window.event) {window.event.cancelBubble = true;}
			}).find('li').off('click').on('click', function () {
				$(this).addClass('selected').siblings('.selected').removeClass('selected');
				$(thisOne).find('input').data('value', $(this).data('value')).val($(this).text()).end().removeClass('selected');
				$(thisOne).find('input').trigger('change');
			});
		}
	});
}
/**
 * 娣诲姞ComboBox浜嬩欢
 * @param panelId锛氳〃鍗曟垨鏌ヨ鏉′欢闈㈡澘鐨刬d
 */
function addEventComboBox(panelId) {
	$(panelId + ' .iFComboBox').find('input[type=text]').off('.comboboxInput').on('focus.comboboxInput', function () {
		$(this).siblings('.iFList').show();
	}).on('blur.comboboxInput', function () {
		var thisOne = $(this);
		setTimeout(function () {
			thisOne.siblings('.iFList').hide();
		}, 200);
	}).end().find('a').off('click').on('click', function () {
		if ($(this).siblings('.iFList').css('display')==='none'){
			$(this).siblings('input[type=text]').focus();
		}
	}).end().find('.iFList li').off('click').on('click', function () {
		$(this).addClass('selected').siblings('.selected').removeClass('selected');
		var value = $(this).text();
		var key = $(this).attr('data-value');
		$(this).parents('.iFList').siblings('input[type=text]').attr({value: value, 'data-value': key}).trigger('change');
	});
}
/**
 * 娣诲姞ComboBoxList浜嬩欢
 * @param id锛歝ombobox鐨刬d
 */
function addEventComboBoxList(id) {
	$(id + '.iFComboBox').find('.iFList li').off('click').on('click', function () {
		$(this).addClass('selected').siblings('.selected').removeClass('selected');
		var value = $(this).text();
		var key = $(this).attr('data-value');
		$(this).parents('.iFList').siblings('input[type=text]').attr('data-value', key);
		$(this).parents('.iFList').siblings('input[type=text]').val(value);
		$(this).parents('.iFList').siblings('input[type=text]').trigger('change');
	});
}
/**
*  涓嬫媺妗嗛�鐢ㄦ柟娉曪紝浼犲叆涓�釜id銆乨ata  鐩存帴灏嗗唴瀹规斁鍏ヤ笅鎷夋
*/
function xlktyff(id,data){
	for(var i=0;i<data.length;i++){
		$("#"+id).append('<li data-value="'+data[i].id+'">'+data[i].name+'</li>');
	}
	selectonclick(id);
}
function selectonclick(id){
	$("#"+id).find('.iFList').on('click', function () {
		if (event.stopPropagation){event.stopPropagation();}else if (window.event) {window.event.cancelBubble = true;}
	}).find('li').off('click').on('click', function () {
		$("#"+id).addClass('selected').siblings('.selected').removeClass('selected');
		$("#"+id).find('input').data('value', $(this).data('value')).val($(this).text()).end().removeClass('selected');
		$("#"+id).find('input').trigger('change');
	});
}
//form鎻愪氦灏嗗瓧绗︿覆杞琷son
function getFormJson(id) {
    var o = {};
    var a = $("#"+id).serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}