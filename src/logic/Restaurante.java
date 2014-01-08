package logic;

import dao.PedidoDao;
import entities.Pedido;
import entities.PedidoItem;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class Restaurante {

    public double recTotal(Date dtInic, Date dtFim) {

        PedidoDao pedidoDao = new PedidoDao();
        List<Pedido> pedidos = pedidoDao.todos();

        Iterator<Pedido> it = pedidos.iterator();
        double receita = 0;

        while (it.hasNext()) {
            Pedido pedido = it.next();
            Date dataPg = pedido.getDtPagto();
            
            if (dataPg != null) {
                if ((dataPg.compareTo(dtInic) > 0) && (dataPg.compareTo(dtFim) < 0)) {
                    Collection<PedidoItem> pedItem = pedido.getPedidoItemCollection();
                    PedidoLogic pedCont = new PedidoLogic();
                    receita += pedCont.pedirNota(pedido);
                }
            }
        }

        return receita;
    }
}
