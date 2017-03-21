<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!-- Main -->

		<div id="main">
			<div class="inner">
				<!-- Header -->
				<header id="header">
					<a href="index.html" class="logo"><strong>커뮤니티</strong></a>
				</header>
				<br />

				<div>
					<div class="title_left">
						<h3>광고 등록</h3>
					</div>
					<form name="uploadFIle" method="POST" enctype="multipart/form-data" onsubmit="return false;">
						<div style="display: inline-block;">
							<div class="select-wrapper">
								<span>광고분류:</span> <select name="categoryId">
									<c:forEach var="category" items="${category }">
										<option value="${category.id }">${category.name }</option>
									</c:forEach>
								</select>
							</div>

							<span>회사명:</span> 
							<input type="text" name="company" style="width: 800px;" /> 
							<span>광고문구:</span> <input type="text" name="slogan" style="width: 800px;" /> 
							<span>광고자:</span> 
							<input type="text" name="userName" style="width: 800px;" /> 
							<span>휴대폰 번호:</span>
							<input type="text" name="phoneNumber" style="width: 800px;" />
							<div align="right">
								<ul class="actions">
									<li><a href="#addMember" class="button special icon fa-plus" data-toggle="modal">회원 검색</a></li>
								</ul>
							</div>
							<input type="file" name="image"/>
						</div>
						<br />
						<textarea id="body" name="content" class="smarteditor2" style="width: 800px; min-height: 100px;"></textarea>
						<br /> 
						<span>광고기간:<input type="date" name="startDate" />~<input type="date" name="endDate" /></span> <br /> <br />
						<button class="button special">저장</button>
						<a href="#" class="button">취소</a>
						<%@ include file="/WEB-INF/views/commons/userAjax.jsp" %>
						</form>
						</div>
			</div>
		</div>
