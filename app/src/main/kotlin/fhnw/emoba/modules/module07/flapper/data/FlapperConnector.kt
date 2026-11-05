package fhnw.emoba.modules.module07.flapper.data

//class FlapperConnector((mqttBroker: String,
//                       val qos: MqttQos = MqttQos.EXACTLY_ONCE){
//
//    private val username: String = "emoba_usr"
//    private val password: String = "WasserBeruf42"
//
//    private val client = Mqtt5Client.builder()
//        .serverHost(mqttBroker)
//        .identifier(UUID.randomUUID().toString())
//        .sslWithDefaultConfig()
//        .simpleAuth()
//        .username(username)
//        .password(UTF_8.encode(password))
//        .applySimpleAuth()
//        .serverPort(8883)
//        .buildAsync()
//
//    fun connectAndSubscribe(topic:              String,
//                            onNewMessage:       (String) -> Unit = {},
//                            onConnectionFailed: () -> Unit = {}) {
//        client.connectWith()
//            .cleanStart(true)
//            .keepAlive(30)
//            .send()
//            .whenComplete { _, throwable ->
//                if (throwable != null) {
//                    onConnectionFailed()
//                } else { //erst wenn die Connection aufgebaut ist, kann subscribed werden
//                    subscribe(topic, onNewMessage)
//                }
//            }
//    }
//
//    fun subscribe(topic:        String,
//                  onNewMessage: (String) -> Unit){
//        client.subscribeWith()
//            .topicFilter(topic)
//            .qos(qos)
//            .noLocal(true)
//            .callback { onNewMessage(it.payloadAsString()) }
//            .send()
//    }
//
//    fun publish(topic:       String,
//                message:     String,
//                onPublished: () -> Unit = {},
//                onError:     () -> Unit = {}) {
//        client.publishWith()
//            .topic(topic)
//            .payload(message.asPayload())
//            .qos(qos)
//            .retain(false)  //Message soll nicht auf dem Broker gespeichert werden
//            .messageExpiryInterval(120)
//            .send()
//            .whenComplete {_, throwable ->
//                if(throwable != null){
//                    onError()
//                }
//                else {
//                    onPublished()
//                }
//            }
//    }
//
//    fun disconnect() {
//        client.disconnectWith()
//            .sessionExpiryInterval(0)
//            .send()
//    }
//}
//
//// praktische Extension Functions
//private fun String.asPayload() : ByteArray = toByteArray(StandardCharsets.UTF_8)
//private fun Mqtt5Publish.payloadAsString() : String = String(payloadAsBytes, StandardCharsets.UTF_8)
