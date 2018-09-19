var UserInfo = function(){
	var rules={
		mn: {
            required: true,
            number:true
        },
        mobles: {
            required: true,
            isMobile:true ,
            isRepeat:$("#cmb-form-id input[name^='mobles']")
        },
        falge: {
            required: true,
            number:true
        }
    };
	var messages = {
		mn: {
            required: "现汇买入价是必填项"
        },
        mobles: {
            required: "通知手机号是必选项"
        },
        falge: {
            required: "通知次数是必填项"
        }
	};
	var userInfoFormValidate ;
	var userInfoValidate = function(){
		var userInfoForm = $("#cmb-form-id");
		userInfoFormValidate = userInfoForm.validate({
//			errorElement: 'span',
//			errorClass: 'help-block',
			errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input
			focusInvalid: false,
			rules: rules,
	        messages:messages,

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
	        	var fgg = document.querySelector("#cmb-form-id");
				var data = new FormData(fgg);
				var moble = "";
				$("#cmb-form-id input[name^='mobles']").each(function(){
					moble+=$(this).val();
					moble+=",";
				});
				data.set("moble",moble.substr(0,moble.length-1));
				console.info(data);
				$.ajax({
					url:App.domain()+"/cmbc/editCMB",
					data:data,
					contentType: false,
	                processData: false,
	                type:"post",
					success: function(da){
						if(da.code==0){
							location.href = location.href;
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
	$(".date-picker").each(function(){
		$(this).find("input").change(function(){
			userInfoFormValidate.element(this);
		});
	});
	
	$(".add-moble").click(function(){
		   var divGroup = $(this).parent();
		   var l = $("input[name^='mobles'").length;
		   var html = '<div class="form-group">'+
				   	'<label class="control-label col-md-2">通知手机号</label>'+
				   	'<div class="col-md-8">'+
				   	'<input type="text" name="mobles_'+l+'" style="margin-bottom:2px;" class="form-control">'+
				   	'</div>'+
				   	'</div>';
				   	$(html).insertAfter(divGroup);
				   	rules["mobles_"+l]={required:true,isMobile:true ,isRepeat:$("#cmb-form-id input[name^='mobles']")}
				   	messages["mobles_"+l]={required:"通知手机号是必选项"}
 				userInfoFormValidate.destroy();
 				userInfoValidate();
	   });
	return {
		init:function(){
			userInfoValidate();
		}
	};
}();
jQuery(document).ready(function(){
	UserInfo.init();
});