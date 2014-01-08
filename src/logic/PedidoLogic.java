/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import dao.PedidoDao;
import entities.Pedido;
import entities.PedidoItem;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;


public class PedidoLogic {
    
    public double pedirNota(Pedido pedido){
        
        Collection<PedidoItem>  itens = pedido.getPedidoItemCollection();
        double valorNota = 0;
        
        Iterator<PedidoItem> it = itens.iterator();
        while(it.hasNext()){
            PedidoItem ped = it.next();
            valorNota += ped.getPrecoItem() * ped.getQuantItem();
        }
        
        return valorNota;
    }
    
    public void cancelarPedido(Pedido pedido){
        pedido.setCancelado(true);
        PedidoDao pedidoDao = new PedidoDao();
        pedidoDao.atualizar(pedido);
    }
    
    public void pendurarNota(Pedido pedido){
        pedido.setPendurado(true);
        PedidoDao pedidoDao = new PedidoDao();
        pedidoDao.atualizar(pedido);
    }
    
    public double pagarNota(Pedido pedido, double valor){
        Date data = new Date();
       
        double valorNota = this.pedirNota(pedido);
        
        if(valor >= valorNota){
            pedido.setPendurado(false);
            pedido.setCancelado(false);
            pedido.setDtPagto(data);
            
            PedidoDao pedidoDao = new PedidoDao();
            pedidoDao.atualizar(pedido);
            return (valor-valorNota);
        }
        
        else
            return -1;
        
    }
}
