package model;

import java.util.List;

/**
 * Created by 1494778 on 2016-02-22.
 */
public class ListClients {
    private List<Client> clients;

    public ListClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
        //
    }

    public void addClientFromServer(Client client){
        clients.add(client);
        //ajouter les clients du serveur
    }
    public void saveClientServeur(){

    }
}
