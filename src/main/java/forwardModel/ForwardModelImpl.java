package forwardModel;

import java.util.*;

/**
 * Created by Damien Anderson on 05/02/16.
 */
public class ForwardModelImpl implements ForwardModel {

    private static final String START = "MI";
    private Map<String, String> table = new HashMap<>();

    @Override
    public List<String> nextStates(String state) {
        List<String> nextStates = new ArrayList<>();
        nextStates.add(ruleOne(state));
        nextStates.add(ruleTwo(state));
        nextStates.addAll(ruleThree(state));
        nextStates.addAll(ruleFour(state));
        nextStates.removeAll(Arrays.asList(""));
        return nextStates;
    }

    @Override
    public List<String> breadthFirstSearch(String goalString) {
        Queue<String> agenda = new LinkedList<>();
        int agendaSize = 0;
        agenda.add(START);

        while (!agenda.isEmpty()) {
            if (agenda.size() > agendaSize) {
                agendaSize = agenda.size();
            }
            String nextState = agenda.remove();

            if (nextState.equals(goalString)) {
                return pathToGoal(nextState);
            }

            List<String> nextStates = nextStates(nextState);
            for (String state : nextStates) {
                table.put(state, nextState);
            }
            agenda.addAll(nextStates);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<String> depthLimitedDFS(String goalString, int depth) {
        Stack<String> agenda = new Stack<>();
        agenda.add(START);
        int currentDepth = 0;

        while (!agenda.isEmpty()) {
            String nextState = agenda.pop();

            if (nextState.equals(goalString)) {
                return pathToGoal(nextState);
            }

            List<String> nextStates = nextStates(nextState);
            for (String state : nextStates) {
                table.put(state, nextState);
            }
            if (currentDepth < depth) {
                currentDepth++;
                agenda.addAll(nextStates);
            }
        }
        return Collections.EMPTY_LIST;
    }

    private List<String> pathToGoal(String goalString) {
        List<String> path = new ArrayList<>();
        while (!goalString.equals(START)) {
            path.add(goalString);
            goalString = table.get(goalString);
        }
        path.add(START);
        Collections.reverse(path);
        table.clear();
        return path;
    }

    @Deprecated
    private List<List<String>> expandPath(List<String> pathToExtend) {
        List<List<String>> extendedPaths = new ArrayList<>();
        List<String> nextStates = this.nextStates(pathToExtend.get(pathToExtend.size() - 1));

        for (String state : nextStates) {
            List<String> temp = new ArrayList<>(pathToExtend);
            temp.add(state);
            extendedPaths.add(pathToExtend);
        }

        return extendedPaths;
    }

    private String ruleOne(String state) {
        if (state.endsWith("I")) {
            return state + "U";
        }
        return "";
    }

    private String ruleTwo(String state) {
        if (state.startsWith("M")) {
            char head = state.charAt(0);
            String body = state.substring(1);
            return head + body + body;
        }
        return "";
    }

    private Set<String> ruleThree(String state) {
        Set<String> nextStates = new HashSet<>();
        String target = "III";

        if (state.contains(target)) {
            for (int i = 0; i < state.length() - 2; i++) {
                if (state.substring(i, i + 3).equals(target)) {
                    nextStates.add(state.substring(0, i) + "U" + state.substring(i + 3));
                }
            }
        }
        return nextStates;
    }

    private Set<String> ruleFour(String state) {
        Set<String> nextStates = new HashSet<>();
        String target = "UU";

        if (state.contains(target)) {
            for (int i = 0; i < state.length() - 1; i++) {
                if (state.substring(i, i + 2).equals(target)) {
                    nextStates.add(state.substring(0, i) + state.substring(i + 2));
                }
            }
        }
        return nextStates;
    }

}
