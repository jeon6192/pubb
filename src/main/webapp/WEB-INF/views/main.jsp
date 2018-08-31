<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
			.main-div > ul {
				margin: 0 auto;
				width: 80%;
				height: 500px;
				border: 1px solid #efefef;
				border-radious: 5px;
				list-style: none;
			}
			li > form > div {
				text-align: center;
				margin: 0 auto;
				width: 100%;
				padding: 0.5em;
			}
			.first-li {
				height: 40%;
			}
			.second-li {
				height: 60%;
			}
			li > div {
				width: 100%;
			}
			.input-div {
				margin-top: 20px;
			}
		</style>
		
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script>
			$(document).ready(function(){
				$("#searchForm").keypress(function (e) {
			        if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
			            $('.submit-btn').click();
			            return false;
			        } else {
			            return true;
			        }
			    });
				
				
			});
			
			function selectPlayer(){
				var name = $('.insert-ID').val();
				
				// 입력값이 없을 시 반응이 없도록 함
				if (name == '') {
					return false;
				}
				
				$.ajax({
			        type : "POST",
			        dataType : 'json', 
			        data : {"name" : name}, 
			        url : "./PUBG/player",
			        success: function(data){
			        	$.map( data, function( val, i ) {
			        		//console.log(data);
			        		var obj = $.parseJSON( val );
			        		console.log(i);
			        		console.log(obj);
		        		});
			        	
			        },
			        error:function(request,status,error){
			            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			            document.write(request.responseText);
			        }
			    });
				
			};
		
		</script>
	</head>
	<body>
		<div class = "main-div">
			<ul>
				<li class = "first-li">
					<form id = "searchForm" onsubmit="selectPlayer(); return false;">
						<div class = "input-div">
							<label for = "userName">Username : </label>
							<input name = "userName" type = "text" class = "insert-ID">
						</div>
						<div class = "btn-div">
							<button type = "submit" class = "submit-btn">입력</button>
						</div>
					</form>
				</li>
				
				<li class = "second-li">
					<div class = "textarea-div">
						
					</div>
				</li>
			</ul>
		</div>
	</body>
</html>