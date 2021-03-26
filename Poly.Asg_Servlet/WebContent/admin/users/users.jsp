<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col mt-4">
	<jsp:include page="/common/inform.jsp"></jsp:include>
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item" role="presentation"><a
			class="nav-link active" id="userEditing-tab" data-toggle="tab"
			href="#userEditing" role="tab" aria-controls="videoEditing"
			aria-selected="true">User Editing</a></li>
		<li class="nav-item" role="presentation"><a class="nav-link"
			id="userList-tab" data-toggle="tab" href="#userList" role="tab"
			aria-controls="userList" aria-selected="false">User List</a></li>

	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="userEditing"
			role="tabpanel" aria-labelledby="userEditing-tab">
			<form action="" method="post">

				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col">
								<div class="form-group">
									<label for="username">Username</label> <input type="text"
										value="${user.username }" class="form-control" name="username"
										id="username" aria-describedby="usernameHid"
										placeholder="Username"> <small id="usernameHid"
										class="form-text text-muted">Username is required</small>
								</div>
								<div class="form-group">
									<label for="password">Password</label> <input type="password"
										class="form-control" name="password" id="password"
										aria-describedby="passwordHid" placeholder="Password">
									<small id="passwordHid" class="form-text text-muted">Password
										is required</small>
								</div>
							</div>
							<div class="col">
								<div class="form-group">
									<label for="fullname">Fullname</label> <input type="text"
										value="${user.fullname }" class="form-control" name="fullname"
										id="fullname" aria-describedby="fullnameHid"
										placeholder="Fullname"> <small id="fullnameHid"
										class="form-text text-muted">Fullname is required</small>
								</div>
								<div class="form-group">
									<label for="email">Email</label> <input type="text"
										value="${user.email }" class="form-control" name="email"
										id="email" aria-describedby="emailHid" placeholder="Email">
									<small id="emailHid" class="form-text text-muted">Email
										is required</small>
								</div>
								<div class="form-check form-check-inline">
									<label for=""> 
									<input type="radio" class="form-check-input" value="true" name="admin"
									 ${user.admin? 'checked':'' } id="status">Admin
									</label> 
									<label for=""> 
									<input type="radio" class="form-check-input" value="false"
										${!user.admin? 'checked':'' } name="admin" id="status">User
									</label>
								</div>
							</div>
						</div>
					</div>
					<div class="card-footer text-muted">
						<button formaction="Admin/UserManagement/create" class="btn btn-primary">Create</button>
						<button formaction="Admin/UserManagement/update" class="btn btn-warning">Update</button>
						<button formaction="Admin/UserManagement/delete" class="btn btn-danger">Delete</button>
						<button formaction="Admin/UserManagement/reset" class="btn btn-primary">Reset</button>
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane fade" id="userList" role="tabpanel"
			aria-labelledby="userList-tab">
			<table class="table table-stripe">
				<tr>
					<td>Username</td>
					<td>Password</td>
					<td>Fullname</td>
					<td>Email</td>
					<td>Role</td>
					<td>&nbsp;</td>
				</tr>
				<c:forEach var="item" items="${users }">
					<tr>
						<td>${item.username }</td>
						<td>${item.password }</td>
						<td>${item.fullname }</td>
						<td>${item.email }</td>
						<td>${item.admin ? 'Admin':'User' }</td>
						<td><a
							href="Admin/UserManagement/edit?username=${item.username }"><i
								class="fa fa-edit" aria-hidden="true"></i>Edit</a> 
							<a href="Admin/UserManagement/delete?username=${item.username }"><i
								class="fa fa-trash" aria-hidden="true"></i>Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</div>
</div>
