package It.polimi.ingsw.Network;

import It.polimi.ingsw.Message.Message;
import It.polimi.ingsw.Message.MessageContent;
import It.polimi.ingsw.Model.Match;
import It.polimi.ingsw.Observer.Observable;
import It.polimi.ingsw.Observer.Observer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The class that represents the client.
 */
public class Client extends Observable implements Observer {

	private String ip;
	private int port;
	private Socket socket;
	private boolean active = true;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;

	/**
	 * The constructor of the client.
	 * @param ip: the address ip.
	 * @param port: the number of port.
	 * @throws IOException: signals that an I/O exception has occurred.
	 */
	public Client(String ip, int port) throws IOException {
		this.ip = ip;
		this.port = port;
		try {
			socket = new Socket(ip, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		outputStream.flush();
		inputStream = new ObjectInputStream(socket.getInputStream());

	}

	/**
	 * Return true if the client is active.
	 * @return true if the client is active.
	 */
	public synchronized boolean isActive() {
		return active;
	}

	public synchronized void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * It creates a thread that read objects from socket.
	 * @param socketIn: Object Input Stream which objects are read.
	 * @return the thread which is cretaed.
	 */
	public Thread asyncReadFromSocket(final ObjectInputStream socketIn) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (isActive()) {
					Object inputObject = null;
					try {
						inputObject = socketIn.readObject();
						Message message=(Message) inputObject;
						if(message.getMessageContent()== MessageContent.CLOSEDCONNECTION){
							setActive(false);
						}
						else{notifyObserver((Message) inputObject);}
					} catch (IOException e) {
						setActive(false);
					}
					catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
		return t;
	}

    /*public Thread asyncWriteToSocket(final Scanner stdin, final PrintWriter socketOut) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isActive()) {
                        String inputline = stdin.nextLine();
                        socketOut.println(inputline);
                        socketOut.flush();
                    }
                } catch (Exception e) {
                    setActive(false);
                }
            }
        });
        t.start();
        return t;
    }*/

	/**
	 * It used to send message on the Object output stream.
	 * @param message: the message to be sent.
	 */
	public void sendMessage(Message message) {
		try {
			outputStream.writeObject(message);
			outputStream.flush();
			outputStream.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main method of the thread.
	 * @throws IOException: signals that an I/O exception has occurred.
	 */
	public void run() throws IOException {
		System.out.println("Connection Established");
		//ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());
		PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
		Scanner stdin = new Scanner(System.in);
		try {
			Thread t0 = asyncReadFromSocket(inputStream);
            /*Thread t1 = asyncWriteToSocket(stdin, socketOut);
            t1.join();*/
			t0.join();
		} catch (InterruptedException | NoSuchElementException e) {
			System.out.println("Connection closed from the client side");
		} finally {
			System.out.println("A player disconnected, closing the Game");

			stdin.close();
			inputStream.close();
			socketOut.close();
			socket.close();
		}
	}

	/**
	 * It is an override of the update method in interface Observer.
	 * @param message: the message to be sent.
	 */
	@Override
	public void update(Message message) {
		sendMessage(message);
	}
}

