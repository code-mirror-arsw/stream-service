# 🎧 Audio & Code Stream Service

Este microservicio proporciona **comunicación en tiempo real** para sesiones colaborativas de entrevistas técnicas. Utiliza WebSockets para habilitar dos funcionalidades clave:

1. **Llamadas de Audio WebRTC** entre **2 a 5 personas** en salas privadas.
2. **Sincronización de Edición de Código** en tiempo real mediante WebSockets binarios.

---

## ✨ Características Principales

- ✅ **Audio en Tiempo Real P2P:** Comunicación entre 2 a 5 usuarios mediante WebRTC.
- ✅ **Sincronización de Texto Colaborativa:** Basado en WebSockets binarios, compatible con CRDTs o motores como Y.js.
- ✅ **Salas Privadas:** Aislamiento completo de cada sala por `roomId`.
- ✅ **Escalabilidad y bajo consumo:** El servidor solo retransmite señalización, no el audio o el código.

---

## ⚙️ Tecnologías Utilizadas

- **Lenguaje:** Java 17+
- **Framework:** Spring Boot 3
- **Audio:** WebRTC (peer-to-peer)
- **WebSocket Textual:** `netty-socketio` (estilo Socket.IO)
- **WebSocket Binario:** `spring-websocket`

---

## 🎤 Transmisión de Audio (WebRTC)

La funcionalidad de audio se basa en WebRTC, permitiendo una comunicación directa entre los participantes sin pasar el flujo de audio por el servidor. El microservicio actúa únicamente como **servidor de señalización**, encargándose de retransmitir los mensajes necesarios para establecer la conexión entre pares.

Cada cliente intercambia ofertas, respuestas y candidatos ICE a través de WebSockets. Una vez completado el intercambio, la conexión de audio se establece de forma P2P (peer-to-peer), lo cual garantiza mínima latencia y eficiencia de red.

---

## 📝 Sincronización de Código en Tiempo Real

El editor de texto compartido funciona mediante **WebSocket binario**. Cada cliente se conecta a una sala específica y transmite en tiempo real los cambios del contenido, que son inmediatamente retransmitidos a todos los participantes conectados a la misma sala.

Esta implementación permite sincronización colaborativa eficiente y es compatible con motores CRDT como **Y.js**. El servidor no interpreta el contenido, simplemente actúa como un canal binario para distribuir los datos entre los usuarios de la sala correspondiente.

---

## 🔐 Seguridad y Control de Acceso

Este microservicio **no gestiona directamente la autenticación**. Las sesiones se controlan mediante tokens que son **verificados previamente por la API Gateway (PIG)**. Solo usuarios autenticados a través del gateway pueden acceder a las funcionalidades de este servicio.

---

## 👥 Participación Simultánea

Este sistema está diseñado para manejar **llamadas grupales de entre 2 y 5 personas**, con lógica optimizada para mantener la estabilidad de las conexiones tanto en la parte de audio como en la colaboración del editor.

---


## diagrama de clases

![image](https://github.com/user-attachments/assets/8d93363d-6bd3-4458-8a81-22cd1af44a66)


