var UserInfo = function(){
	
	var userInfoValidate = function(){
		var userInfoForm = $("#user-info-form-id");
		var userInfoFormValidate = userInfoForm.validate({
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
	                isChinese:true,
	                minMbLength:2,
	                maxMbLength:6
	            },
	            sex: {
	                required: true
	            },
	            age: {
	                required: true,
	                maxlength:3,
	                minlength:2,
	                digits:true,
	                max:150
	            },
	            idCar: {
	            	required: true,
	            	maxlength:18,
	            	minlength:15,
	            	isIdCardNo:true
	            },
	            birthday: {
	            	required: true,
	            	isDate:true
	            },
	            tel: {
	            	required: true,
	            	maxlength:11,
	            	digits:true,
	            	minlength:11,
	            	isMobile:true
	            },
	            nations: {
	            	required: true
	            },
	            height: {
	            	required: true,
	            	maxlength:3,
	            	digits:true,
	            	minlength:2
	            },
	            weight: {
	            	required: true,
	            	maxlength:3,
	            	digits:true,
	            	minlength:2
	            },
	            education: {
	            	required: true
	            },
	            graduate: {
	            	required: true
	            },
	            specialty: {
	            	required: true
	            },
	            graduation: {
	            	required: true,
	            	isDate:true
	            }
	        },
	        messages: {
	        	name: {
	                required: "姓名是必填项"
	            },
	            sex: {
	                required: "性别是必选项"
	            },
	            age: {
	                required: "年龄是必填项",
	                maxlength:"年龄最多3位数",
	                minlength:"年龄最少2位数",
	                digits:"年龄必须是数字",
	                max:"年龄最大为150岁"
	            },
	            idCar: {
	            	required: "身份证号是必填项",
	            	maxlength:"身份证号最多{0}位",
	            	minlength:"身份证号最少{0}位"
	            },
	            birthday: {
	            	required: "生日是必填项"
	            },
	            tel: {
	            	required: "手机号是必填项",
	            	maxlength:"手机号最多11位",
	            	digits:"手机号必须是数字",
	            	minlength:"手机号最少11位"
	            },
	            nations: {
	            	required: "民族是必选项"
	            },
	            height: {
	            	required: "身高是必填项",
	            	maxlength:"身高最多{0}位数",
	            	digits:"身高必须是数字",
	            	minlength:"身高最少{0}位数"
	            },
	            weight: {
	            	required: "体重是必填项",
	            	maxlength:"体重最多{0}位数",
	            	digits:"体重必须是数字",
	            	minlength:"体重最少{0}位数"
	            },
	            education: {
	            	required: "学历是必选项"
	            },
	            graduate: {
	            	required: "毕业院校是必填项"
	            },
	            specialty: {
	            	required: "专业是必填项"
	            },
	            graduation: {
	            	required: "毕业时间是必填项"
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
	        	alert(55);
	        	App.blockUI({"zIndex":1000});
	        	var ctx = App.domain();
	        	var fgg = document.querySelector("#user-info-form-id");
				var data = new FormData(fgg);
				console.info(data);
				$.ajax({
					url:App.domain()+"/userInfo/addOrEditUserInfo",
					data:data,
					contentType: false,
	                processData: false,
	                type:"post",
					success: function(da){
						if(da.code==0){
							var oTable = $('#datatable_ajax_user_info').dataTable();
							oTable.fnDraw();
							$("#user-info-form-id")[0].reset();
							$(".add-or-edit-modal-class").modal("hide");
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
		
		$(".date-picker").each(function(){
			$(this).find("input").change(function(){
				userInfoFormValidate.element(this);
			});
		});
		
	};
	
	return {
		init:function(){
			userInfoValidate();
		}
	};
}();
jQuery(document).ready(function(){
	UserInfo.init();
});