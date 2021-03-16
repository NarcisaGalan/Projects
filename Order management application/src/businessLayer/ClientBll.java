package businessLayer;



import java.util.List;
import java.util.NoSuchElementException;

import dataAccessLayer.ClientDAO;
import models.Client;

public class ClientBll {

	private ClientDAO clientDao;

	public ClientBll() {
		this.clientDao = new ClientDAO();
	}

	public Client findById(int id) {
		Client client = null;
		client = clientDao.findById(id);
		if (client == null) {
			throw new NoSuchElementException("The element with the id " + id + " doesnt exist!");
		}
		return client;
	}

	public void deleteClient(String name) {
		int id = clientDao.getIdFromName(name);
		clientDao.deleteClient(id);
	}

	public void insert(Client client) {
		clientDao.insert(client);
	}

	public void update(Client client) {
		clientDao.update(client);
	}

	public List<Client> selectAll() {
		return clientDao.selectAll();
	}
}
