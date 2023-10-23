import com.spark.simplified.rpc.{NettyRpcEndpointRef, NettyRpcEnv, RpcEndpoint}
import org.scalatest.funsuite.AnyFunSuite

class NettyRpcEndpointRefSuite extends AnyFunSuite {

  test("NettyRpcEndpointRef send") {
    val rpcEnv = new NettyRpcEnv()
    rpcEnv.start()

    var receivedMessage: String = null
    rpcEnv.register("testEndpoint", new RpcEndpoint {
      override def receive(message: String): Unit = {
        receivedMessage = message
      }
    })

    val endpointRef = new NettyRpcEndpointRef("testEndpoint", rpcEnv)
    endpointRef.send("Hello, world!")

    assert(receivedMessage == "Hello, world!")

    rpcEnv.stop()
  }

  test("NettyRpcEndpointRef ask") {
    val rpcEnv = new NettyRpcEnv()
    rpcEnv.start()

    rpcEnv.register("testEndpoint", new RpcEndpoint {
      override def receive(message: String): Unit = {
        if (message == "question") {
          rpcEnv.send("answer")
        }
      }
    })

    val endpointRef = new NettyRpcEndpointRef("testEndpoint", rpcEnv)
    val future = endpointRef.ask("question")

    assert(future.value.get.get == "answer")

    rpcEnv.stop()
  }
}