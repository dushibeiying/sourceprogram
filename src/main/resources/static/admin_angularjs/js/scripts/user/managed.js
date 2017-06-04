var UserManaged = function () {

    var initData = function () {
    	$('#users').empty();
       $.get("http://localhost/rest/users/managed", function(response){
    	   users = response.data;
    	   for(var i = 0; i< users.length; i++){
    		   var user = users[i];
    		   var userRow = $("<tr></tr>").append('<td><label class="mt-checkbox mt-checkbox-outline mt-checkbox-single"><input type="checkbox" class="group-checkable userIdCheckbox" data-userId="'+user.id+'" /><span></span></label></td>')
	    		   .append("<td>"+user.mobile+"</td>")
	    		   .append("<td>"+user.nickname+"</td>")
	    		   .append("<td>"+user.registerTime+"</td>");
    		   $('#users').append(userRow);
    	   }
    	   
       });
    }
    
    var initEvents = function () {
    	$('#new_user_action').click(function(){
    		$('#users').empty();
    		 
		});   	
    	
    	$('#delete_user_action').click(function(){    		
    		var userIds = [];
	    	$(".userIdCheckbox:checked").each(function() {
	    		userIds.push($(this).attr("data-userId"));
	        }); 
	    	$.ajax({
	            type: "POST",
	            url: "http://localhost:80/rest/users/deleteById",
	            contentType: "application/json; charset=utf-8",
	            data: JSON.stringify(userIds),
	            dataType: "json",
	            success: function (data) {	               
	                	initData();	               
	            },
	        });
    	});
    	
    	$('#export_action').click(function(){
    		var url = "http://localhost:80/rest/users/exportById?";
    		var hasId = false;
	    	$(".userIdCheckbox:checked").each(function() {
	    		url += "ids=" + $(this).attr("data-userId") + "&";
	    		hasId = true;
	        });
	    	if(hasId){
	    		$('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
	    	}
	    });
    }


    return {

        //main function to initiate the module
        init: function () {

        	initData();
        	initEvents();
        }

    };

}();