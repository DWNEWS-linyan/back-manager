var SysUser = function(){
	
	var sysUserValidate = function(){
		var sysUserForm = $(".add-sys-user-form");
		var sysUserFormValidate = sysUserForm.validate({
//			errorElement: 'span',
//			errorClass: 'help-block',
			errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input
			focusInvalid: false,
			rules: {
				name: {
	                required: true,
	                remote: {
	                    url: App.domain()+"/system/remoteUserName",     //后台处理程序
	                    type: "post",               //数据发送方式
	                    dataType: "json",           //接受数据格式   
	                    data: {                     //要传递的数据
	                        username: function() {
	                            return $(".add-sys-user-form input[name='name']").val();
	                        }
	                    }
	                }
	            },
	            pass: {
	                required: true
	            },
	            userinfoid: {
	                required: true
	            }
	        },
	        messages: {
	        	name: {
	                required: "用户名是必填项",
	                remote:"用户名重复了"
	            },
	            pass: {
	                required: "密码是必选项"
	            },
	            userinfoid: {
	                required: "用户是必选项"
	            }
	        },
	        invalidHandler: function(event, validator) { //display error alert on form submit  
	        },

	        highlight: function(element) { // hightlight error inputs
	            $(element)
	                .closest('.form-group').addClass('has-error'); // set error class to the control group
	        },
	        unhighlight: function (element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },
	        success: function(label) {
	            label.closest('.form-group').removeClass('has-error');
	            label.remove();
	        },

	        errorPlacement: function(error, element) {
//	            error.insertAfter(element.closest('.input-icon'));
//	        	var cont = $(element).parent('.input-group');
//                if (cont) {
//                    cont.after(error);
//                } else {
//                    element.after(error);
//                }
	        	if (element.parents('.mt-radio-list').length>0 || element.parents('.mt-checkbox-list').length>0) {
                    if (element.parents('.mt-radio-list')[0]) {
                        error.appendTo(element.parents('.mt-radio-list')[0]);
                    }
                    if (element.parents('.mt-checkbox-list')[0]) {
                        error.appendTo(element.parents('.mt-checkbox-list')[0]);
                    }
                } else if (element.parents('.mt-radio-inline').length>0 || element.parents('.mt-checkbox-inline').length>0) {
                    if (element.parents('.mt-radio-inline')[0]) {
                        error.appendTo(element.parents('.mt-radio-inline')[0]);
                    }
                    if (element.parents('.mt-checkbox-inline')[0]) {
                        error.appendTo(element.parents('.mt-checkbox-inline')[0]);
                    }
                } else if (element.parent(".input-group").size() > 0) {
                    error.insertAfter(element.parent(".input-group"));
                } else if (element.attr("data-error-container")) { 
                    error.appendTo(element.attr("data-error-container"));
                } else {
                    error.insertAfter(element); // for other inputs, just perform default behavior
                }
	        },

	        submitHandler: function(form) {
	        	App.blockUI({"zIndex":1000});
	        	var ctx = App.domain();
	        	var d = $(".add-sys-user-form").serializeArray();
     			$.ajax({
					url:App.domain()+"/system/addSysUser",
					data:d,
	                type:"post",
					success: function(da){
						if(da.code==0){
							var oTable = $('#datatable_ajax_sysuser').dataTable();
							oTable.fnDraw();
							$("#add-sys-user").modal("hide");
						}else{
							bootbox.alert(da.mes);
						}
						App.unblockUI();
					},
					error: function(a,b,c){
						var status = a.status;
						if(b=='timeout'){
							bootbox.alert("不好意思  超时了 ");
						}else{
							App.errorAlertStatus(status);
						}
						App.unblockUI();
					}
				});
	        	return false;
//	        	$('.login-form').attr("action",ctx+"/sys/login");
//	            form.submit(); // form validation success, call ajax form submit
	        }
		});
	};
	
	return {
		init:function(){
			sysUserValidate();
		}
	};
}();
jQuery(document).ready(function(){
	SysUser.init();
});