import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice(val name: String, val category: String) {

    var deviceStatus = "offline"
        protected set

    open val deviceType = "unknown"

    var deviceTurnOnCount = 0
        protected set

    open fun turnOn() {
        if (deviceStatus != "on") {
            deviceStatus = "on"
            deviceTurnOnCount++
            println("$name is turned on. Device turn on count: $deviceTurnOnCount")
        } else {
            println("$name is already on.")
        }
    }

    open fun turnOff() {
        if (deviceStatus == "on") {
            deviceStatus = "off"
            println("$name turned off.")
        } else {
            println("$name is already off.")
        }
    }

    protected fun checkDeviceStatus(action: String, block: () -> Unit) {
        if (deviceStatus == "on") {
            block()
        } else {
            println("Cannot perform $action. $name is off.")
        }
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart TV"

    private var speakerVolume = 2
        set(value) {
            if (value in 0..100) {
                field = value
            }
        }

    private var channelNumber = 1
        set(value) {
            if (value in 0..200) {
                field = value
            }
        }

    fun increaseSpeakerVolume() {
        checkDeviceStatus("increase speaker volume") {
            speakerVolume++
            println("Speaker volume increased to $speakerVolume.")
        }
    }

    fun nextChannel() {
        checkDeviceStatus("next channel") {
            channelNumber++
            println("Channel number increased to $channelNumber.")
        }
    }

    fun previousChannel() {
        checkDeviceStatus("previous channel") {
            channelNumber--
            println("Channel number decreased to $channelNumber.")
        }
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart Light"

    private var brightnessLevel = 0
        set(value) {
            if (value in 0..100) {
                field = value
            }
        }

    fun increaseBrightness() {
        checkDeviceStatus("increase brightness") {
            brightnessLevel++
            println("Brightness increased to $brightnessLevel.")
        }
    }

    fun decreaseBrightness() {
        checkDeviceStatus("decrease brightness") {
            brightnessLevel--
            println("Brightness decreased to $brightnessLevel.")
        }
    }
}

class SmartHome {
    val smartTvDevice: SmartTvDevice = SmartTvDevice("Android TV", "Entertainment")
    val smartLightDevice: SmartLightDevice = SmartLightDevice("Google Light", "Utility")

    fun turnOnTv() {
        smartTvDevice.turnOn()
    }

    fun turnOffTv() {
        smartTvDevice.turnOff()
    }

    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }

    fun decreaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }

    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }

    fun changeTvChannelToPrevious() {
        smartTvDevice.previousChannel()
    }

    fun turnOnLight() {
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        smartLightDevice.turnOff()
    }

    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }

    fun decreaseLightBrightness() {
        smartLightDevice.decreaseBrightness()
    }

    fun printSmartTvInfo() {
        println("Smart TV device name: ${smartTvDevice.name}")
        println("Smart TV device category: ${smartTvDevice.category}")
        println("Smart TV device type: ${smartTvDevice.deviceType}")
        println("Smart TV device status: ${smartTvDevice.deviceStatus}")
        println("Smart TV device turn on count: ${smartTvDevice.deviceTurnOnCount}")
    }

    fun printSmartLightInfo() {
        println("Smart Light device name: ${smartLightDevice.name}")
        println("Smart Light device category: ${smartLightDevice.category}")
        println("Smart Light device type: ${smartLightDevice.deviceType}")
        println("Smart Light device status: ${smartLightDevice.deviceStatus}")
        println("Smart Light device turn on count: ${smartLightDevice.deviceTurnOnCount}")
    }
}

fun main() {
    val smartHome = SmartHome()

    // Encender la TV
    smartHome.turnOnTv()

    // Intentar aumentar el volumen (debería funcionar)
    smartHome.increaseTvVolume()

    // Intentar cambiar el canal (debería funcionar)
    smartHome.changeTvChannelToNext()

    // Apagar la TV
    smartHome.turnOffTv()

    // Intentar aumentar el volumen (no debería funcionar)
    smartHome.increaseTvVolume()

    // Encender la luz
    smartHome.turnOnLight()

    // Intentar aumentar el brillo (debería funcionar)
    smartHome.increaseLightBrightness()

    // Apagar la luz
    smartHome.turnOffLight()

    // Intentar disminuir el brillo (no debería funcionar)
    smartHome.decreaseLightBrightness()

    // Encender la TV de nuevo
    smartHome.turnOnTv()

    // Imprimir información de los dispositivos
    smartHome.printSmartTvInfo()
    smartHome.printSmartLightInfo()
}