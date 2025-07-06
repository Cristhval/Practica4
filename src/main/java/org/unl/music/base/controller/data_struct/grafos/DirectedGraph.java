package org.unl.music.base.controller.data_struct.grafos;

import org.unl.music.base.controller.data_struct.list.LinkedList;

public class DirectedGraph extends Graph {
    private Integer nro_vertex;
    private Integer nro_edge;
    private LinkedList<Adjacency> list_adjacencies [];

    public DirectedGraph(Integer nro_vertex){
        this.nro_vertex = nro_vertex;
        this.nro_edge = 0;
        list_adjacencies = new LinkedList[nro_vertex +1];
        for (int i = 1; i<=nro_vertex; i++){
            list_adjacencies[i] = new LinkedList<>();
        }
    }


    public LinkedList<Adjacency>[] getList_adjacencies() {
        return list_adjacencies;
    }

    public void setList_adjacencies(LinkedList<Adjacency>[] list_adjacencies) {
        this.list_adjacencies = list_adjacencies;
    }

    @Override
    public Integer nro_vertex() {
        return this.nro_vertex;
    }

    @Override
    public Integer nro_edge() {

        return this.nro_edge;
    }

    public void setNro_vertex(Integer nro_edge){
        this.nro_edge = nro_edge;
    }

    @Override
    public Adjacency exists_Edge(Integer o, Integer d) {
        Adjacency band = null;
        if (o.intValue() <= nro_vertex.intValue()&&d.intValue()<= nro_vertex.intValue()){
            LinkedList<Adjacency> list = list_adjacencies[o];
            if(!list.isEmpty()){
                Adjacency[] matrix = list.toArray();
                for (Adjacency adj : matrix){
                    if (adj.getDestiny().intValue() == d.intValue()){
                        band = adj;
                        break;
                    }
                }
            }
        }

        return band;
    }



    @Override
    public Float weight_edge(Integer o, Integer d) {
        Adjacency adj= exists_Edge(o,d);
        if(exists_Edge(o, d) !=null){
            return adj.getWeigth();
        }

        return  Float.NaN;

    }

    @Override
    public void insert(Integer o, Integer d) {
        insert(o,d,Float.NaN);
    }

    @Override
    public void insert(Integer o, Integer d, Float weigth) {
        if (o.intValue() <= nro_vertex.intValue()&&d.intValue()<= nro_vertex.intValue()){
            if (exists_Edge(o,d) == null){
                nro_edge++;
                Adjacency aux = new Adjacency();
                aux.setWeigth(weigth);
                aux.setDestiny(d);
                list_adjacencies[o].add(aux);
            }
        }else{
            throw new ArrayIndexOutOfBoundsException("Vertex origin or destiny index out");
        }
    }

    @Override
    public LinkedList<Adjacency> adjacencies(Integer o) {
        return list_adjacencies[o];
    }
}
