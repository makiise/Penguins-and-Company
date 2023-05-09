package pgdp.penguinDate;

import pgdp.tree.Tree;

public class PenguinDateGenerator {

    private Tree<Penguin> tree;

    public PenguinDateGenerator(Tree<Penguin> tree) {
        this.tree = tree;
    }

    public boolean canGoOnADate(Penguin p1, Penguin p2) {
        Penguin lca = tree.LCA(p1, p2);
        int dist1 = tree.distanceBetweenNodes(p1, lca);
        int dist2 = tree.distanceBetweenNodes(p2, lca);
        
        if(dist1 < p1.getAllowance()) {
            return false;
        }
        if(dist2 < p2.getAllowance()) {
            return false;
        }
        return true;
    }

    public Tree<Penguin> getTree() {
        return this.tree;
    }

}
