/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "pedido_item")
@NamedQueries({
    @NamedQuery(name = "PedidoItem.findAll", query = "SELECT p FROM PedidoItem p")})
public class PedidoItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PedidoItemPK pedidoItemPK;
    @Basic(optional = false)
    @Column(name = "quant_item")
    private int quantItem;
    @Basic(optional = false)
    @Column(name = "preco_item")
    private float precoItem;
    @JoinColumn(name = "id_pedido", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "id_item", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Item item;

    public PedidoItem() {
    }

    public PedidoItem(PedidoItemPK pedidoItemPK) {
        this.pedidoItemPK = pedidoItemPK;
    }

    public PedidoItem(PedidoItemPK pedidoItemPK, int quantItem, float precoItem) {
        this.pedidoItemPK = pedidoItemPK;
        this.quantItem = quantItem;
        this.precoItem = precoItem;
    }

    public PedidoItem(Pedido pedido, Item item) {
        this.pedidoItemPK = new PedidoItemPK(pedido, item);
    }

    public PedidoItemPK getPedidoItemPK() {
        return pedidoItemPK;
    }

    public void setPedidoItemPK(PedidoItemPK pedidoItemPK) {
        this.pedidoItemPK = pedidoItemPK;
    }

    public int getQuantItem() {
        return quantItem;
    }

    public void setQuantItem(int quantItem) {
        this.quantItem = quantItem;
    }

    public float getPrecoItem() {
        return precoItem;
    }

    public void setPrecoItem(float precoItem) {
        this.precoItem = precoItem;
    }

    public Pedido getPedido() {
        return pedido;
    }


    public Item getItem() {
        return item;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidoItemPK != null ? pedidoItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoItem)) {
            return false;
        }
        PedidoItem other = (PedidoItem) object;
        if ((this.pedidoItemPK == null && other.pedidoItemPK != null) || (this.pedidoItemPK != null && !this.pedidoItemPK.equals(other.pedidoItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication6.PedidoItem[ pedidoItemPK=" + pedidoItemPK + " ]";
    }
    
}
