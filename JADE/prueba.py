import serial, time
arduino = None
def run():
    global arduino
    urlArchivo = "hello.txt"
    archivo = open(urlArchivo, "r")

    entrada=archivo.read()

    
    if(entrada is not None):

        if(entrada == "1"):
            #
            arduino.flushInput()
            arduino.flushOutput()
            arduino.write(entrada.encode())
            rawString = arduino.readline()
            arduino.flushInput()
            arduino.flushOutput()

            archivo.close()
            
            flushDoc(urlArchivo)

            #escribiendo datos de sensores

            archivo = open(urlArchivo, "w")

            archivo.write(str(rawString))

                
            
            
            print(str(rawString))
        elif(entrada == "0"):
            flushDoc(urlArchivo)
            arduino.write(entrada.encode())
            


def flushDoc(pfile):
    with open(pfile, "w"):
        pass

if __name__ == "__main__":
    arduino = serial.Serial("COM4", 9600)
    time.sleep(1)
    while(True):
        run()
