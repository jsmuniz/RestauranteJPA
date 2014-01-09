package testes;

import logic.PedidoLogic;
import logic.Restaurante;
import dao.ClienteDao;
import dao.ItemDao;
import dao.MesaDao;
import dao.PedidoDao;
import dao.PedidoItemDao;
import entities.Cliente;
import entities.Mesa;
import entities.Pedido;
import entities.PedidoItem;
import entities.Item;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TesteDao {

    public static void main(String[] args) {

        
        /*
         //SALVAR MESA
         Mesa mesa = new Mesa();
         MesaDao mesaDao = new MesaDao();
        
         mesaDao.salvar(mesa);
         */
        
        /*
         //SALVAR CLIENTE
         Cliente cliente = new Cliente();
         ClienteDao clienteDao = new ClienteDao();
        
         cliente.setNome("José");
         cliente.setTelefone("(32) 9999-9999");
        
         clienteDao.salvar(cliente);
         */
        
        /*
         //SALVAR ITEM
         Item item = new Item();
         ItemDao itemDao = new ItemDao();
        
         item.setNome("Refrigerante");
         item.setPcUnit(3);
         item.setDescricao("Bebida");
        
         itemDao.salvar(item);
         
        
        
         //SALVAR PEDIDO
         Pedido pedido = new Pedido();
         Pedido pedido2 = new Pedido();
         Date d = new Date();
         PedidoDao pedidoDao = new PedidoDao();

         ClienteDao clienteDao = new ClienteDao();
         Cliente cliente = clienteDao.pesquisarPorId(1);
         
         MesaDao mesaDao = new MesaDao();
         Mesa mesa = mesaDao.pesquisarPorId(1);

         pedido.setData(d);
         pedido.setIdCliente(cliente);
         pedido.setIdMesa(mesa);
         
         pedido2.setData(d);
         pedido2.setIdCliente(cliente);
         pedido2.setIdMesa(mesa);

         pedidoDao.salvar(pedido);
         pedidoDao.salvar(pedido2);
        */
         
        /*
         //TESTE SALVAR PEDIDOITEM
         PedidoDao pedidoDao = new PedidoDao();
         Pedido pedido = pedidoDao.pesquisarPorId(1);
        
         ItemDao itemDao = new ItemDao();
         Item item = itemDao.pesquisarPorId(1);
        
         PedidoItemDao pedidoItemDao = new PedidoItemDao();
         PedidoItem pedidoItem = new PedidoItem(pedido,item);
         pedidoItem.setPrecoItem(item.getPcUnit());
         pedidoItem.setQuantItem(2);
        
         pedidoItemDao.salvar(pedidoItem);
         
         pedido = pedidoDao.pesquisarPorId(2);
         
         PedidoItem pedidoItem2 = new PedidoItem(pedido,item);
         pedidoItem2.setPrecoItem(item.getPcUnit());
         pedidoItem2.setQuantItem(3);
         
         pedidoItemDao.salvar(pedidoItem2);
         */
         
         
        /*
         //getPedidoItemCollection()
         PedidoDao pedidoDao = new PedidoDao();
         Pedido pedido = pedidoDao.pesquisarPorId(2);
         Collection<PedidoItem> pedidoitem = pedido.getPedidoItemCollection();
        

         Iterator it = pedidoitem.iterator();
        
         while(it.hasNext()){
            
         PedidoItem pedidoit = (PedidoItem) it.next();
         System.out.println("Pedido Nº: " + pedidoit.getPedido().getId());
         System.out.println("Cliente: " + pedidoit.getPedido().getIdCliente().getNome());
         System.out.println("Item: " + pedidoit.getItem().getNome());
         System.out.println("Quantidade: " + pedidoit.getQuantItem());
         System.out.println("Preco: R$ " + pedidoit.getPrecoItem());
         }
        */
         
        /*
         //PAGAR e PEDIR NOTA
       
         PedidoDao pedidoDao = new PedidoDao();
         Pedido pedido = pedidoDao.pesquisarPorId(2);
         PedidoLogic ped = new PedidoLogic();
        
    
         double valor = 25;
         double troco;
         troco = ped.pagarNota(pedido, valor);
        
         System.out.println("Valor Nota: R$ " + ped.pedirNota(pedido) );
         System.out.println("Valor Entregue: R$ " + valor);
         System.out.println("Troco: R$ " + troco);      
         */
        
        /*
         //RECEITA TOTAL
         Date dtInic = new Date();
         dtInic.setDate(1);
         dtInic.setMonth(0);
         dtInic.setYear(110);

         Date dtFim = new Date();
         dtFim.setDate(1);
         dtFim.setMonth(0);
         dtFim.setYear(115);

         Restaurante restaurante = new Restaurante();
         System.out.println("Receita: R$ " + restaurante.recTotal(dtInic, dtFim));
         */
        
        /*
        //UTILIZAÇÃO DE HQL
        List<Cliente> lista = new ClienteDao().todosClientesComDDD("32");
        System.out.println("oi");
        */
    }

}
