package dam.psp;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CirculoSVR implements ICirculo{

	private final double PI=Math.PI;
	private double _radio;
	
	public CirculoSVR(Registry registro) {
		System.out.println("creando objeto circulo y su inscripcion en el registro");
		try {
			
			registro.bind("Circulo",(ICirculo)UnicastRemoteObject.exportObject(this,0));
			System.out.println("Finalizado el objeto del registro en remoto");
			
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void set_radio(double _radio) throws RemoteException {
		this._radio= _radio;
		
	}

	@Override
	public double area() throws RemoteException {
		return PI*_radio*_radio;
	}

	@Override
	public double longitud() throws RemoteException {
		return 2*PI*_radio;
	}
	
	
	public static void main(String[] args) throws RemoteException {
		final int puerto=8888;
		//para que funcione con otro cliente y que no solo funcione en local
		System.setProperty("java.rmi.server.hostname","192.168.3.63");
		System.setProperty("java.net.preferIPV4Stack","true");
		
		
		Registry registro= LocateRegistry.createRegistry(puerto);
		
		new CirculoSVR(registro);
		
	}
	

}
