var Login = function() {

    var handleLogin = function() {

        $('.login-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
            	userName: {
                    required: true
                },
                userPass: {
                    required: true
                },
                remember: {
                    required: false
                }
            },

            messages: {
            	userName: {
                    required: "用户名是必填项."
                },
                userPass: {
                    required: "密码是必填项."
                }
            },

            invalidHandler: function(event, validator) { //display error alert on form submit  
            	$('.alert-danger', $('.login-form')).find("span").html("请输入您的用户名和密码. ");
                $('.alert-danger', $('.login-form')).show();
            },

            highlight: function(element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function(error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function(form) {
            	App.blockUI({"zIndex":1000});
            	var ctx = App.domain();
            	$('.login-form').attr("action",ctx+"/sys/login");
                form.submit(); // form validation success, call ajax form submit
            }
        });

        $('.login-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.login-form').validate().form()) {
                	App.blockUI({"zIndex":1000});
                	var ctx = App.domain();
                	$('.login-form').attr("action",ctx+"/sys/login");
                    $('.login-form').submit(); //form validation success, call ajax form submit
                }
                return false;
            }
        });
        
        $(".verification-input-class").keyup(function(){
        	var verification = $(this).val();
        	if(verification!=''&&verification != null){
        		$.ajax({
    				url:App.domain()+"/sys/keyDown",
    				data:{verification:verification},
    				success: function(da){
    					console.info(da.code);
    					if(da.code==0){
    						$(".flagcheckclass").show();
    					}else{
    						$(".flagcheckclass").hide();
    					}
    				},
    				error: function(a,b,c){
    					$(".flagcheckclass").hide();
    					console.info(a+"出错了");
    				}
    			});
        	}
        });

        $(".verification-img-class").on("click",function(){
        	$(this).attr("src",App.domain()+"/sys/login/yanZhengMa?l="+Math.random());
        });
        
        
    }

    var handleForgetPassword = function() {
        $('.forget-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
            	telPhoneCallBack: {
                    required: true,
                    maxlength:11,
                    minlength:11
                },
		        telPhone: {
		        	required: true,
		        	maxlength:4,
		        	minlength:4
		        }
            },

            messages: {
            	telPhoneCallBack: {
                    required: "手机号是必填项.",
                    maxlength:"手机号是11位",
                    minlength:"手机号是11位"
                },
                telPhone: {
	            	required: "找回码是必填项.",
	            	maxlength:"找回码是4位",
	                minlength:"找回码是4位"
	            }
            },

            invalidHandler: function(event, validator) { //display error alert on form submit   
            	var errorList = validator.errorList;
//            	for(var i in errorList){
            		var i = 0 ;
            		var ele = errorList[i].element;
            		var message = errorList[i].message;
            		var method = errorList[i].method;
//            	}
            	$(".text-tell-phone").html(message).css({
        			"color":"red"
        		});
            },

            highlight: function(element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function(error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function(form) {
            	if(falsg){
            		$(".text-tell-phone").html("请获取找回码").css({
            			"color":"red"
            		});
            		return false;
            	}
            	App.blockUI({"zIndex":1000});
            	var telPhone = $("input[name='telPhone']").val();
            	var telPhoneCallBack = $("input[name='telPhoneCallBack']").val();
            	//这里应该传到后台
            	$.ajax({
    				url:App.domain()+"/sys/yanzhengzhaohuima",
    				data:{telPhoneCallBack:telPhoneCallBack,telPhone:telPhone},
    				success: function(da){
    					App.unblockUI();
    					console.info(da.code);
    					if(da.code==0){
    						jQuery('.forget-form').hide();
    	                    jQuery('.forget-passnew-form').show();
    					}else{
    						$(".text-tell-phone").html(da.mes).css({
    							"color":"red"
    						});
    					}
    				},
    				error: function(a,b,c){
    					App.unblockUI();
    					$(".text-tell-phone").html("很尴尬，出错了。。。。");
    					console.info(a+"出错了");
    				}
    			});
            	return false;
            }
        });
        var falsg = true;
        var callbackdoejs = "";
        $(".colback-span").on("click",".colback-pass",function(){
        	var telPhoneCallBack = $("input[name='telPhoneCallBack']").val();
        	if(telPhoneCallBack!=null&&telPhoneCallBack!=undefined&&telPhoneCallBack!=''&&telPhoneCallBack.length==11&&(/^1[34578]\d{9}$/.test(telPhoneCallBack))){
        		$(".text-tell-phone").html("");
        		$("input[name='telPhoneCallBack']").attr("readonly","readonly");
        		App.blockUI({"zIndex":1000});
        		var ctx = App.domain();
        		$.ajax({
    				url:App.domain()+"/sys/retrievePass",
    				data:{telPhoneCallBack:telPhoneCallBack},
    				success: function(da){
    					falsg = false;
    					App.unblockUI();
    					console.info(da.code);
    					if(da.code==0){
    						callbackdoejs = da.callbackdoe;
    						console.info(da.callbackdoe);
    						var thisHtml = $(".colback-span").html();
    		            	var str = "<button class='btn' onclick='javascript:return false;'><span class='ms-dao'>120</span>s 可以重新发送</button>";
    		            	$(".colback-span").html(str);
    		            	var fg = setInterval(function() {
    		            		var dao = $(".ms-dao").html();
    		            		if(dao!=0){
    		            			dao--;
    		                		$(".ms-dao").html(dao);
    		            		}else{
    		            			$(".colback-span").html(thisHtml);
    		            			$("input[name='telPhoneCallBack']").attr("readonly",false);
    		            			window.clearInterval(fg);
    		            		}
    		            	}, 1000)
    					}else if(da.code==1||da.code==2){
    						$("input[name='telPhoneCallBack']").attr("readonly",false);
    						$(".text-tell-phone").html(da.mes);
    					}else{
    						$("input[name='telPhoneCallBack']").attr("readonly",false);
    						$(".text-tell-phone").html("很尴尬，出错了。。。。");
    					}
    				},
    				error: function(a,b,c){
    					App.unblockUI();
    					$("input[name='telPhoneCallBack']").attr("readonly",false);
    					$(".text-tell-phone").html("很尴尬，出错了。。。。");
    					console.info(a+"出错了");
    				}
    			});
        	}else{
        		$(".text-tell-phone").html("请输入正确的手机号!").css({
        			"color":"red"
        		});
        	}
        	return false;
        });
        
        jQuery('#forget-password').click(function() {
            jQuery('.login-form').hide();
            jQuery('.forget-form').show();
        });

        jQuery('#back-btn').click(function() {
            jQuery('.login-form').show();
            jQuery('.forget-form').hide();
            jQuery('.forget-passnew-form').hide();
        });
        jQuery('#back-btn-tow').click(function() {
            jQuery('.login-form').show();
            jQuery('.forget-form').hide();
            jQuery('.forget-passnew-form').hide();
        });

    }

    var handleForgetNewPassword = function() {
        $('.forget-passnew-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
            	passnew: {
                    required: true
                },
                passnewagien: {
		        	required: true
		        }
            },

            messages: {
            	passnew: {
                    required: "手机号是必填项."
                },
                passnewagien: {
	            	required: "找回码是必填项."
	            }
            },

            invalidHandler: function(event, validator) { //display error alert on form submit   
            	var errorList = validator.errorList;
//            	for(var i in errorList){
            		var i = 0 ;
            		var ele = errorList[i].element;
            		var message = errorList[i].message;
            		var method = errorList[i].method;
//            	}
            	$(".text-new-pass").html(message).css({
        			"color":"red"
        		});
            },

            highlight: function(element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function(error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function(form) {
            	var passnew = $("input[name='passnew']").val();
            	var passnewagien = $("input[name='passnewagien']").val();
            	if(passnew!=passnewagien){
            		$(".text-new-pass").html("两次输入的密码不一致，请重新输入").css({"color":"red"});
            		return false;
            	}
            	var telPhoneCallBack = $("input[name='telPhoneCallBack']").val();
            	App.blockUI({"zIndex":1000});
            	//这里应该传到后台
            	$.ajax({
    				url:App.domain()+"/sys/saveNewPass",
    				data:{newPass:passnew,tel:telPhoneCallBack},
    				success: function(da){
    					App.unblockUI();
    					console.info(da.code);
    					if(da.code==0){
    						jQuery('.login-form').show();
    						jQuery('.forget-form').hide();
    	                    jQuery('.forget-passnew-form').hide();
    					}else{
    						$(".text-new-pass").html(da.mes).css({"color":"red"});
    					}
    				},
    				error: function(a,b,c){
    					App.unblockUI();
    					$(".text-new-pass").html("很尴尬，出错了。。。。").css({"color":"red"});
    					console.info(a+"出错了");
    				}
    			});
            }
        });
    }

    
    return {
        //main function to initiate the module
        init: function() {
            handleLogin();
            handleForgetPassword();
            handleForgetNewPassword();
        }
    };

}();

jQuery(document).ready(function() {
    Login.init();
});