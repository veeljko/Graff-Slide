package strategy.concretes;

import repository.graff_components.GraffNode;
import strategy.EmptySpaceStrategy;
import tabs.elements.GraffSlideElement;

import java.util.ArrayList;

public class SecondEmptySpaceCalculateStrategy implements EmptySpaceStrategy {
    @Override
    public int calculateEmptySpace(ArrayList<GraffNode> elements, int w, int h) {
        int[][] matrix = new int[w + 1][h + 1];
        for (int i=0;i<650;i++) for (int j=0;j<450;j++) matrix[i][j] = 0;
        for (GraffNode child : elements) {
            GraffSlideElement element = (GraffSlideElement) child;
            int x1 = (int) element.getLocation().getX();
            int y1 = (int) element.getLocation().getY();
            int x2 = x1 + (int) element.getDimension().getWidth();
            int y2 = y1 + (int) element.getDimension().getHeight();
            for (int i=x1;i<=x2;i++){
                for (int j=y1;j<=y2;j++){
                    matrix[i][j] = 1;
                }
            }
        }
        int cnt = 0;
        for (int i=0;i<=w;i++) for (int j=0;j<=h;j++) if (matrix[i][j] == 0) cnt++;
        return cnt;
    }
}
