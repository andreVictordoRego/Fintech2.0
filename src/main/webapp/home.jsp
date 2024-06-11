<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<script src="../assets/js/color-modes.js"></script>
		<meta charset="UTF-8">
		<title>Fintech</title>

		<link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/heroes/">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
		<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/css/heroes.css" rel="stylesheet">

		<%@ include file="header-login.jsp" %>
	</head>

	<body>
		<main class="main">
			<div class="container container-heroes col-xxl-8 px-4 py-5">
				<div class="row flex-lg-row-reverse align-items-center g-5 p-5">
					<div class="col-10 col-sm-8 col-lg-6">
						<img src="./undraw_projections_re_ulc6.svg" class="d-block mx-lg-auto img-fluid"
							alt="Bootstrap Themes" width="700" height="500" loading="lazy">
					</div>
					<div class="col-lg-6">
						<h1 class="display-5 fw-bold text-body-emphasis lh-1 mb-3">Transforme sua relação com o dinheiro
							com a Fintech
						</h1>
						<p class="lead my-5">Dê adeus aos dias de incerteza financeira e dê as boas-vindas a uma nova
							era de controle e
							prosperidade. Equipado com tecnologia de ponta e recursos inteligentes, estamos aqui para
							simplificar sua
							vida financeira, desde o gerenciamento de despesas diárias até o planejamento de metas de
							longo prazo. Com
							nossa plataforma intuitiva e acessível, você estará no comando de suas finanças como nunca
							antes.</p>
						<div class="d-grid gap-2 d-md-flex justify-content-evenly">
							<button type="button" class="btn btn-primary btn-lg px-4 me-md-2">Cadastre-se</button>
							<button type="button" class="btn btn-outline-secondary btn-lg px-4" data-bs-toggle="modal"
								data-bs-target="#staticBackdrop">Login</button>
						</div>
					</div>
				</div>
			</div>
		</main>

		<div class="modal" id="staticBackdrop" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title text-primary" id="staticBackdropLabel">Olá de novo!</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form>
							<div class="mb-3 mx-3">
								<label for="exampleInputEmail1" class="form-label">Email</label>
								<input type="email" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp">
							</div>
							<div class="mb-3 mx-3">
								<label for="exampleInputPassword1" class="form-label">Senha</label>
								<input type="password" class="form-control" id="exampleInputPassword1">
							</div>
							<div class="d-flex justify-content-end mx-3">
								<a href="#">Esqueci minha senha</a>
							</div>
							<div class="d-flex justify-content-center my-3">
								<button type="submit" class="btn btn-primary">Entrar</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<script src="./resources/js/bootstrap.bundle.min.js"></script>
	</body>
	
	<footer>
		<@ include file="footer.jsp" %>
	</footer>

	</html>