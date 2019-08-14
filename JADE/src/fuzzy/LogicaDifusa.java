/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy;

import static java.lang.Thread.sleep;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Rule;

/**
 * Test parsing an FCL file
 * @author pcingola@users.sourceforge.net
 */
public class LogicaDifusa {
    

    public double iluminar(int luminocidad, int presencia) throws Exception {
        // Load from 'FCL' file
        String fileName = ""
                + "src/fuzzy/Archivo FCL.fcl";
        FIS fis = FIS.load(fileName, true);
        // Error while loading?
        if (fis == null) {
            System.err.println("Can't load file: '" + fileName + "'");
        }
        // Set inputs
        fis.setVariable("luminocidad", luminocidad);
        fis.setVariable("presencia", presencia);

        // Evaluate
        fis.evaluate();

        // Show
        JFuzzyChart.get().chart(fis.getFunctionBlock("fuzzy"));
        

        Double x = fis.getVariable("activacion").getLatestDefuzzifiedValue();
        System.err.println("Para los valores de salida el grado de pertenencia es: " + x);
        
        // Show rules
        for (Rule r : fis.getFunctionBlock("fuzzy").getFuzzyRuleBlock("No1").getRules()) {
            System.out.println(r);
        }
                
        return x;
    }
}