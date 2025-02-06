# 📱 Android Video App

## 📌 Sobre o Projeto
Este projeto foi desenvolvido como parte de um desafio técnico para a vaga de Desenvolvedor Android. A aplicação permite que o usuário faça login com uma conta Google, pesquise e reproduza vídeos do YouTube, crie listas de reprodução e marque vídeos como favoritos. Além disso, a aplicação exibe os termos de uso do YouTube em uma WebView e implementa notificações locais simulando o recebimento de um link de vídeo.

## 🛠 Tecnologias Utilizadas
- **Linguagem**: Kotlin
- **Arquitetura**: MVVM + Clean Architecture
- **UI Framework**: Jetpack Compose
- **Injeção de Dependência**: Koin
- **Armazenamento Local**: Room
- **Notificações**: Notificações locais com mock
- **Integração com API**: YouTube Data API

## 📂 Estrutura do Projeto
O projeto segue a arquitetura MVVM combinada com os princípios do Clean Code, garantindo modularidade, escalabilidade e facilidade de manutenção. A estrutura do código está organizada da seguinte forma:

```
app/
├── data/          # Camada de dados (repositórios, fontes de dados, API, modelos de dados)
├── domain/        # Camada de domínio (casos de uso, modelos de domínio)
├── presentation/  # Camada de apresentação (ViewModels, UI com Jetpack Compose)
├── di/            # Módulos de injeção de dependência
└── utils/         # Classes utilitárias e auxiliares
```

## 🎯 Funcionalidades Implementadas
- ✅ **Login com conta Google**
- ✅ **Busca de vídeos no YouTube**
- ✅ **Reprodução de vídeos**
- ✅ **Criação de listas de reprodução**
- ✅ **Favoritar vídeos**
- ✅ **Exibição dos termos de uso do YouTube**
- ✅ **Notificação local simulando recebimento de um link de vídeo**

## 🔍 Por que MVVM + Clean Architecture ao invés de MVI?
A escolha do **MVVM + Clean Architecture** se deve a alguns fatores:

1. **Separação de responsabilidades**: A estrutura do MVVM combinada com Clean Code facilita a manutenção e escalabilidade do código.
2. **Testabilidade**: Como a camada de UI (View) não se comunica diretamente com os casos de uso ou a lógica de negócios, podemos testar as ViewModels e os casos de uso isoladamente.
3. **Simplicidade e flexibilidade**: Diferente do MVI, que exige um gerenciamento mais complexo de estados imutáveis e ações, o MVVM permite um fluxo de dados mais natural e menos burocrático para o desenvolvimento de aplicativos Android.
4. **Reatividade**: Utilizamos StateFlow e LiveData para reatividade e melhor gestão de estados na camada de apresentação.

## 🔧 Configuração do Ambiente
Para rodar o projeto, siga os passos abaixo:

1. Clone o repositório:
   ```sh
   git clone https://github.com/HalineCouto/desafio_iCasei
   ```
2. Abra o projeto no Android Studio (recomendado: versão mais recente com suporte ao Kotlin e Jetpack Compose).

3. Execute o projeto em um dispositivo físico ou emulador.

## 📜 Licença
Este projeto foi desenvolvido para fins de avaliação técnica e não possui licença pública.

---

📧 **Contato:** Caso tenha dúvidas ou queira discutir sobre a implementação, entre em contato pelo e-mail: **haline.c.goncalves@gmail.com**.

