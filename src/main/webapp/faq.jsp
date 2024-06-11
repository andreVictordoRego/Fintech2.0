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
		<link href="${pageContext.request.contextPath}/resources/css/faq.css" rel="stylesheet">

		<%@ include file="header.jsp" %>
	</head>

	<body>
        <main class="main">
            <div class="container container-heroes col-xxl-8 px-4 py-5">
                <div class="row flex-lg-row-reverse align-items-center g-5 p-5">
                    <div class="col-lg-6">
                        <h4 class="d-flex justify-content-center my-5">FAQ - Frequently Asked Questions</h4>
                        <div class="d-grid gap-2 mb-2">
                            <a class="btn btn-light text-start text-primary" data-bs-toggle="collapse"
                                href="#collapseExample1" role="button" aria-expanded="false"
                                aria-controls="collapseExample1">
                                Como posso adicionar um novo lançamento à minha conta?
                            </a>
                        </div>
                        <div class="collapse" id="collapseExample1">
                            <div class="card card-body">
                                Para adicionar um novo lançamento, basta navegar até a seção de lançamentos no menu
                                superior. Em seguida,preencha os detalhes relevantes, como o tipo de laçamento, data e hora,
                                a categoria, o valor e adicione uma descrição se quiser. Outra forma é clicar no botão "Novo
                                Lançamento" encontrado na parte superior do seu dashboard.
                            </div>
                        </div>
                        <div class="d-grid gap-2 mb-2">
                            <a class="btn btn-light text-start text-primary" data-bs-toggle="collapse"
                                href="#collapseExample2" role="button" aria-expanded="false"
                                aria-controls="collapseExample2">
                                Posso categorizar minhas despesas e receitas?
                            </a>
                        </div>
                        <div class="collapse" id="collapseExample2">
                            <div class="card card-body">
                                Sim, nosso aplicativo permite que você categorize suas despesas e receitas para uma melhor
                                organização financeira. Você pode escolher entre uma variedade de categorias pré-definidas.
                            </div>
                        </div>
                        <div class="d-grid gap-2 mb-2">
                            <a class="btn btn-light text-start text-primary" data-bs-toggle="collapse"
                                href="#collapseExample3" role="button" aria-expanded="false"
                                aria-controls="collapseExample3">
                                Como faço para acompanhar meu orçamento mensal?
                            </a>
                        </div>
                        <div class="collapse" id="collapseExample3">
                            <div class="card card-body">
                                Nosso aplicativo oferece uma ferramenta de acompanhamento de orçamento que permite visualizar seu progresso ao longo do mês. Você pode consultar seu extrato e histórico de transações para entender melhor seus padrões de gastos e fazer ajustes conforme necessário.
                            </div>
                        </div>
                        <div class="d-grid gap-2 mb-2">
                            <a class="btn btn-light text-start text-primary" data-bs-toggle="collapse"
                                href="#collapseExample4" role="button" aria-expanded="false"
                                aria-controls="collapseExample4">
                                Qual é a política de privacidade do aplicativo em relação aos meus dados financeiros?
                            </a>
                        </div>
                        <div class="collapse" id="collapseExample4">
                            <div class="card card-body">
                                Respeitamos sua privacidade e protegemos seus dados financeiros de acordo com os mais altos padrões de segurança. Nossa política de privacidade detalha como seus dados são coletados, usados e protegidos. Você pode revisar nossa política de privacidade em nosso site ou dentro do próprio aplicativo.
                            </div>
                        </div>
                        <div class="d-grid gap-2 mb-2">
                            <a class="btn btn-light text-start text-primary" data-bs-toggle="collapse"
                                href="#collapseExample5" role="button" aria-expanded="false"
                                aria-controls="collapseExample5">
                                Posso acessar o aplicativo em mais de um dispositivo?
                            </a>
                        </div>
                        <div class="collapse" id="collapseExample5">
                            <div class="card card-body">
                                R: Sim, nosso aplicativo oferece suporte para acessar sua conta em múltiplos dispositivos. Você pode fazer login em qualquer dispositivo compatível com seu nome de usuário e senha, permitindo que você gerencie suas finanças de forma conveniente, onde quer que esteja.
                            </div>
                        </div>
                    </div>
    
                    <div class="col-10 col-sm-8 col-lg-6">
                        <img src="./undraw_questions_re_1fy7.svg" class="d-block mx-lg-auto img-fluid"
                            alt="Bootstrap Themes" width="700" height="500" loading="lazy">
                    </div>
                </div>
            </div>
        </main>
		<script src="./resources/js/bootstrap.bundle.min.js"></script>
	</body>
    <footer>
        <%@ include file="footer.jsp" %>
    </footer>

	</html>