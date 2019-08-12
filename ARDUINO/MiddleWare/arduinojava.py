#python -m pip install PySerial   para instalar serial
# o descargarlo de aqui https://github.com/pyserial/pyserial
import serial, time

arduino = serial.Serial("COM4", 9600)
time.sleep(2)
arduino.write(0)
arduino.close()
