# EMSI Bot

An AI-powered Telegram chatbot built with Java, Spring Boot, Spring AI, and the Model Context Protocol (MCP).

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.5.9-6DB33F?logo=springboot" />
  <img src="https://img.shields.io/badge/Spring_AI-1.1.0--M4-6DB33F?logo=spring" />
  <img src="https://img.shields.io/badge/OpenAI-Integrated-412991?logo=openai" />
  <img src="https://img.shields.io/badge/MCP-Enabled-blue" />
  <img src="https://img.shields.io/badge/Maven-Wrapper-C71A36?logo=apachemaven" />
</p>

## Overview

EMSI Bot is an intelligent conversational assistant designed to interact with users through Telegram.

Built with Java 21 and Spring Boot, the application integrates Spring AI with an OpenAI language model to generate contextual responses. Through the Model Context Protocol (MCP), the assistant can also access external tools provided by a dedicated MCP server.

The project demonstrates the integration of conversational AI, tool calling, and modular backend architecture within a Java ecosystem.

## Features

- **Telegram Integration:** Receives and responds to messages through Telegram.
- **AI-Powered Conversations:** Generates contextual responses using OpenAI through Spring AI.
- **MCP Tool Integration:** Connects the AI model to external tools through the Model Context Protocol.
- **Modular Architecture:** Separates the Telegram bot application from the MCP server.
- **Maven Wrapper:** Simplifies project execution without requiring a separate Maven installation.

## Architecture

```text
                  Telegram User
                        |
                        | Message
                        v
                 +-------------+
                 |   EMSI Bot  |
                 | Spring Boot |
                 |  Spring AI  |
                 +-------------+
                        |
               +--------+--------+
               |                 |
               v                 v
         +-----------+     +-------------+
         | OpenAI API|     |  MCP Client |
         +-----------+     +-------------+
                                 |
                                 | Tool Calls
                                 v
                          +-------------+
                          | MCP Server  |
                          +-------------+
```

### How It Works

1. A user sends a message through Telegram.
2. The Telegram bot receives the message.
3. Spring AI forwards the conversation to the OpenAI model, providing access to the available MCP tools.
4. When necessary, the model requests a tool execution through the MCP client.
5. The MCP server processes the request and returns the result.
6. The model generates its final response.
7. The bot delivers the response to the Telegram user.

## Tech Stack

| Category | Technology |
|---|---|
| Programming Language | Java 21 |
| Backend Framework | Spring Boot 3.5.9 |
| AI Framework | Spring AI 1.1.0-M4 |
| AI Provider | OpenAI |
| Tool Integration | Model Context Protocol (MCP) |
| Messaging Platform | Telegram Bot API |
| Telegram Integration | telegrambots-spring-boot-starter |
| Build Tool | Apache Maven |
| Build Automation | Maven Wrapper |

## Project Structure

```text
emsi-bot/
│
├── src/
│   └── Main Telegram bot application
│
├── mcp-server/
│   └── MCP server module
│
├── .mvn/
│   └── wrapper/
│
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── .gitattributes
```

## Getting Started

### Prerequisites

Make sure you have the following:

- Java Development Kit (JDK) 21.
- A Telegram bot token obtained from [@BotFather](https://t.me/BotFather).
- An OpenAI API key.
- Git.

### 1. Clone the Repository

```bash
git clone https://github.com/OTHM341/emsi-bot.git
cd emsi-bot
```

### 2. Configure Environment Variables

Set the required environment variables:

**Linux / macOS:**

```bash
export OPENAI_API_KEY="your-openai-api-key"
export TELEGRAM_BOT_TOKEN="your-telegram-bot-token"
export TELEGRAM_BOT_USERNAME="your-telegram-bot-username"
```

**Windows PowerShell:**

```powershell
$env:OPENAI_API_KEY="your-openai-api-key"
$env:TELEGRAM_BOT_TOKEN="your-telegram-bot-token"
$env:TELEGRAM_BOT_USERNAME="your-telegram-bot-username"
```

### 3. Start the MCP Server

Open a terminal and run:

```bash
cd mcp-server
../mvnw spring-boot:run
```

Keep the server running.

### 4. Start the Telegram Bot

Open a second terminal from the project root directory:

```bash
./mvnw spring-boot:run
```

On Windows, use:

```powershell
.\mvnw.cmd spring-boot:run
```

### 5. Start a Conversation

Open Telegram, find your bot, and send it a message.

## Configuration

The application uses environment variables to manage sensitive credentials.

| Variable | Description |
|---|---|
| `OPENAI_API_KEY` | OpenAI API authentication key |
| `TELEGRAM_BOT_TOKEN` | Telegram bot authentication token |
| `TELEGRAM_BOT_USERNAME` | Telegram bot username |

**Security Notice:** Never hardcode API keys, bot tokens, or other sensitive credentials in your source code or commit them to version control.

## Running Tests

Execute the following command to run the Maven test lifecycle:

```bash
./mvnw test
```

## Troubleshooting

| Problem | Suggested Solution |
|---|---|
| Telegram bot does not respond | Verify the bot token and username, then check the application logs. |
| OpenAI authentication error (401) | Verify that the API key is valid and correctly configured. |
| MCP tools are unavailable | Ensure that the MCP server is running and that the client connection is correctly configured. |
| Java release version error | Verify that JDK 21 is installed and active using `java -version`. |
| Port already in use | Change the application port or stop the process occupying it. |

## Contributing

Contributions and suggestions are welcome.

1. Fork the repository.
2. Create a feature branch: `git checkout -b feature/my-feature`.
3. Implement your changes.
4. Run the available tests: `./mvnw test`.
5. Submit a pull request with a clear description of your changes.

## Author

**Othmane Moutaouakil**

Computer Engineering Graduate – EMSI
