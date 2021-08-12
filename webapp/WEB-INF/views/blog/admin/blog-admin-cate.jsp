<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>


</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>


		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			
						<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">


//화면 로딩되기 직전
$(document).ready(function() {
	console.log("화면 로딩 직전");

	//ajax 요청하기
	fetchList();

});


//카테고리 삭제
$("#cateList").on("click", ".btnCateDel", function() {
	console.log("삭제버튼 클릭");
	
	var no = $(this).data("no");
	
	console.log(no);
	
	//ajax로 삭제
	$.ajax({
		url : "${pageContext.request.contextPath }/${authUser.id}/admin/category/delete",
		type : "get",
		//contentType : "application/json",
		data : {no: no}, //cateNo 넘기기
		
		dataType : "json",
		success : function(count){
			/*성공시 처리해야될 코드 작성*/
			console.log(count);
			
			if(count === 1) {
				$("#t-"+no).remove();
			} else {
				alert("삭제할 수 없습니다.");
			}
		
		
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

});




//카테고리 추가
$("#btnAddCate").on("click", function() {
	console.log("카테고리 추가 버튼 클릭");
	
	var categoryVo = {
		cateName : $("[name='name']").val(),
		description : $("[name='desc']").val()
	}
	
	//console.log(categoryVo);
	
	
	$.ajax({
		url : "${pageContext.request.contextPath }/${authUser.id}/admin/category/insert",
		type : "get",
		//contentType : "application/json",
		data : categoryVo, 
		
		dataType : "json",
		success : function(cateVo){
			console.log(cateVo);
			render(cateVo, "up");
			
			
			//입력폼 초기화
			$("[name='name']").val("");
			$("[name='desc']").val("");
			
		
		
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
});






//리스트 가져오기
function fetchList() {
	$.ajax({
		//보낼때
		url : "${pageContext.request.contextPath }/${authUser.id}/admin/category/list", //요청 url을 의미
		type : "post", //어차피 주소창 치고 가는거 아니라서 사용자한테 안보임, get 해도 됨 //데이터 전송방식
		//contentType : "application/json",
		//data : {name: "홍길동"},

		//받을때
		dataType : "json", // 서버에서 받아올 데이터를 어떤 형태로 해석할 것인지
		success : function(blogCateList) { //Ajax 통신에 성공했을 때 실행되는 이벤트
			/*성공시 처리해야될 코드 작성*/
			console.log(blogCateList);

			//화면에 그리기
			for (var i = 0; i < blogCateList.length; i++) {
				render(blogCateList[i], "down");
			}

		},
		error : function(XHR, status, error) { //Ajax 통신에 실패했을 때 실행되는 이벤트.
			console.error(status + " : " + error);
		}
	});
}



//카테고리 한개씩 렌더링
function render(categoryVo, type) {

	var str = "";
	str += '<tr id="t-' + categoryVo.cateNo + '">';
	str += '	<td>' + categoryVo.cateNo + '</td>';
	str += '	<td>' + categoryVo.cateName + '</td>';
	str += '	<td>' + categoryVo.cntP + '</td>';
	str += '	<td>' + categoryVo.description + '</td>';
	str += '    <td class="text-center">';
	str += '    	<img data-no="' + categoryVo.cateNo + '"    class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg">';
	str += '    </td>';
	str += '</tr>';

	if (type === 'down') {
		$("#cateList").append(str);
	} else if (type === 'up') {
		$("#cateList").prepend(str);
	} else {
		console.log("방향을 지정해 주세요");
	}

};

</script>










</html>