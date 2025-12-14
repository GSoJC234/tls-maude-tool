package mta.visualizer.info;

import java.util.ArrayList;
import java.util.List;

public class Requirement {

    private List<Integer> requirements = null;

    public Requirement() {
        requirements = new ArrayList<Integer>();
    }

    public int getRequirementIdx(int idx){
        return requirements.get(idx);
    }

    public void addRequirement(int requirementIdx){
        requirements.add(requirementIdx);
    }

    public int getRequirementsCount(){
        return requirements.size();
    }

    public boolean isValid(){
        for (Integer requirementIdx : requirements) {
            if (requirementIdx > 0x36){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Integer requirementIdx : requirements){
            sb.append("Requirement Index: ");
            sb.append(requirementIdx);
            sb.append("\n");
        }
        return sb.toString();
    }
}
