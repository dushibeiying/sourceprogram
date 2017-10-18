var BookManaged = function () {

    var initData = function () {
    	$('#books').empty();
       $.get("http://localhost/rest/books/managed", function(response){
    	   books = response.data;
    	   for(var i = 0; i< books.length; i++){
    		   var book = books[i];
    		   var bookRow = $("<tr></tr>").append('<td><label class="mt-checkbox mt-checkbox-outline mt-checkbox-single"><input type="checkbox" class="group-checkable bookIdCheckbox" data-bookId="'+book.id+'" /><span></span></label></td>')
	    		   .append("<td>"+book.name+"</td>")
	    		   .append("<td>"+book.isbn+"</td>")
	    		   .append("<td>"+book.author+"</td>")
	    		   .append("<td>"+book.press+"</td>")
	    		   .append("<td>"+book.type+"</td>");
    		   $('#books').append(bookRow);
    	   }
    	   
       });
    }
    
    var initEvents = function () {
    	$('#add_book_action').click(function(){
    		$('#books').empty();   		 
		});   		
    	$('#delete_book_action').click(function(){    		
    		var bookIds = []; 
	    	$(".bookIdCheckbox:checked").each(function() {
	    		bookIds.push($(this).attr("data-bookId"));
	        });	    	
	    	if(bookIds.length>0){
	    		console.log(bookIds.length);
		    	$.ajax({
		            type: "POST",
		            url: "http://localhost:80/rest/users/deleteById",
		            contentType: "application/json; charset=utf-8",
		            data: JSON.stringify(bookIds),
		            dataType: "json",
		            success: function (data) {	               
		                	initData();	               
		            },
		        });
	    	}
    	});
    	
    	$('#export_action').click(function(){
    		var url = "http://localhost:80/rest/users/exportById?";
    		var hasId = false;
	    	$(".bookIdCheckbox:checked").each(function() {
	    		url += "ids=" + $(this).attr("data-bookId") + "&";
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