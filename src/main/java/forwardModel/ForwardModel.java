package forwardModel;

import java.util.List;

/**
 * Created by Damien Anderson on 05/02/16.
 */
public interface ForwardModel {

    List<String> nextStates(String state);

    List<String> breadthFirstSearch(String goalString);

    List<String> depthLimitedDFS(String goalString, int depth);

}
