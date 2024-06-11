<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en" data-bs-theme="auto">
<head>
<script src="../resources/js/color-modes.js"></script>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">

<title>Fintech</title>

<link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/headers/">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">

<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/cadastro.css"
	rel="stylesheet">

<%@ include file="header-login.jsp" %>
</head>
<body>

	<body>
        <div class="d-flex">
            <div class="form d-flex flex-column p-5">
                <div class="row">
                    <div class="col-lg-6 d-flex flex-column justify-content-center align-items-center">
                        <label for="fotoPerfil" class="form-label">Foto de Perfil</label>
                        <div class="photo-upload justify-content-center m-5">
                            <div class="photo-preview"></div>
                            <button type="button" class="btn-remove-photo text-danger">&times;</button>
                        </div>
                        <input type="file" class="form-control" id="fotoPerfil" accept="image/jpeg, image/png">
                        <button type="button" class="btn btn-primary mt-2" id="uploadFotoBtn">Upload Foto</button>
                        <small id="fotoPerfilHelp" class="form-text text-muted p-3 mt-3">
                            A foto de perfil deve estar em formato JPG ou PNG, com tamanho máximo de 2 MB e resolução mínima de 400x400 pixels para garantir qualidade e compatibilidade.
                        </small>
                    </div>
                    <form class="col-lg-6 flex-grow-1">
                        <h4>CADASTRO</h4>
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" class="form-control" id="nome" aria-describedby="nomeHelp">
                        </div>
                        <div>
                            <label for="cpf" class="form-label">CPF</label>
                            <input type="text" class="form-control" id="cpf">
                        </div>
                        <div class="row mb-3">
                            <div class="col">
                                <label for="dataNascimento" class="form-label">Data de Nascimento</label>
                                <input type="date" class="form-control" id="dataNascimento">
                            </div>
                            <div class="col">
                                <label for="genero" class="form-label">Gênero</label>
                                <select class="form-select" id="genero">
                                    <option value="masculino">Masculino</option>
                                    <option value="feminino">Feminino</option>
                                    <option value="outro">Outro</option>
                                </select>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col">
                                <label for="exampleInputEmail1" class="form-label">Email</label>
                                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col">
                                <label for="exampleInputPassword1" class="form-label">Senha</label>
                                <input type="password" class="form-control" id="exampleInputPassword1">
                            </div>
                            <div class="col">
                                <label for="exampleInputPassword2" class="form-label">Confirmar Senha</label>
                                <input type="password" class="form-control" id="exampleInputPassword2">
                            </div>
                        </div>
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">Aceito os termos e condições de uso</label>
                        </div>
                        <div class="d-flex justify-content-end">
                            <button type="button" class="btn btn-secondary m-2">Cancelar</button>
                            <button type="submit" class="btn btn-primary m-2">Cadastrar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

		    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
            <script src="${pageContext.request.contextPath}/resources/js/foto-perfil.js"></script>

</body>

<footer>
    <@ include file="footer.jsp" %>
</footer>

</html>
