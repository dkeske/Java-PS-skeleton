/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.KlijentTransfer;
import transfer.ServerTransfer;

/**
 *
 * @author Daniel
 */
public class Komunikacija {

    private Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    private static Komunikacija instance;

    public static Komunikacija vratiInstancu() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public Komunikacija() {
        try {
            this.socket = new Socket("localhost", 9000);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Podesio sam out!!!");
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void posaljiZahtev(KlijentTransfer kt) throws IOException {
        out.writeObject(kt);
    }

    public ServerTransfer procitajOdgovor() throws IOException, ClassNotFoundException {
        return (ServerTransfer) in.readObject();
    }
}
