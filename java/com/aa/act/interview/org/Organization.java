package com.aa.act.interview.org;

import java.util.Collection;
import java.util.Optional;

public abstract class Organization {

    private Position root;
    
    public Organization() {
        root = createOrganization();
    }
    
    protected abstract Position createOrganization();
    
    /**
     * hire the given person as an employee in the position that has that title
     * 
     * @param person
     * @param title
     * @return the newly filled position or empty if no position has that title
     */
    public Optional<Position> hire(Name person, String title)
    {
        return findAndFillPosition(this.root, title, person);
    }

    public Optional<Position> findAndFillPosition(Position position, String title, Name person)
    {
        if (position.getTitle().equals(title))
        {
            position.setEmployee(Optional.of(new Employee(person)));
            return Optional.of(position);
        }

        Collection<Position> directReports = position.getDirectReports();
        for (Position directReport : directReports)
        {
            Optional<Position> result = findAndFillPosition(directReport, title, person);
            if(result != null){
                return result;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return printOrganization(root, "");
    }
    
    private String printOrganization(Position pos, String prefix) {
        StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
        for(Position p : pos.getDirectReports()) {
            sb.append(printOrganization(p, prefix + "  "));
        }
        return sb.toString();
    }
}
