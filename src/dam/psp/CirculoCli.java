package dam.psp;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CirculoCli {

	//public static String SRV="192.168.3.63";
	public static String SRV="192.168.3.57";
	public static int PORT=8888;
	
	
	public static void main(String[] args) {
		ICirculo circulo=null;
		System.out.println("Localizando en la red el registro de objetos remotos....");
		
		Registry registro;
		try {
			registro = LocateRegistry.getRegistry(SRV,PORT);

			//if(registro!=null){
				circulo=(ICirculo)registro.lookup("Circulo");
			//}
				if(circulo!=null){
					circulo.set_radio(20);
					System.out.println("Longitud circulo= "+circulo.longitud()+"\nArea cir= "+circulo.area());
				}
		
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}	
				
	}

}
