package org.unl.music.base.controller.data_struct.grafos;

public class UndirectedGraph extends DirectedGraph{

    public UndirectedGraph(Integer nro_vertex){
        super(nro_vertex);
    }

    @Override
    public void insert(Integer o, Integer d, Float weigth) {
        if (o.intValue() <= nro_vertex().intValue()&&d.intValue()<= nro_vertex().intValue()){
            if (exists_Edge(o,d) == null){
                setNro_vertex(nro_edge()+1);
                //origen
                Adjacency aux = new Adjacency();
                aux.setWeigth(weigth);
                aux.setDestiny(d);
                getList_adjacencies()[o].add(aux);
                //destino
                Adjacency auxD = new Adjacency();
                auxD.setWeigth(weigth);
                auxD.setDestiny(o);
                getList_adjacencies()[d].add(auxD);
            }
        }else{
            throw new ArrayIndexOutOfBoundsException("Vertex origin or destiny index out");
        }
    }
}
