/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class PedidoItemPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_pedido")
    private int idPedido;
    @Basic(optional = false)
    @Column(name = "id_item")
    private int idItem;

    public PedidoItemPK() {
    }

    public PedidoItemPK(Pedido pedido, Item item) {
        this.idPedido = pedido.getId();
        this.idItem = item.getId();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido pedido) {
        this.idPedido = pedido.getId();
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(Item item) {
        this.idItem = item.getId();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPedido;
        hash += (int) idItem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoItemPK)) {
            return false;
        }
        PedidoItemPK other = (PedidoItemPK) object;
        if (this.idPedido != other.idPedido) {
            return false;
        }
        if (this.idItem != other.idItem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication6.PedidoItemPK[ idPedido=" + idPedido + ", idItem=" + idItem + " ]";
    }
    
}
