package smvDebugger.panel;

/*Generated by MPS */

import javax.swing.JPanel;
import jetbrains.mps.project.MPSProject;
import org.fbme.lib.iec61499.declarations.CompositeFBTypeDeclaration;
import smvDebugger.model.Counterexample;
import smvDebugger.visualization.BacktraceService;
import smvDebugger.visualization.SystemHighlighter;
import smvDebugger.panel.mvc.DebugPanelModel;
import smvDebugger.panel.items.DebugPanel;

public class DebugPanelService {
  public JPanel run(final MPSProject project, final CompositeFBTypeDeclaration compositeFb, final Counterexample counterexample) {
    final BacktraceService backtraceService = new BacktraceService(project, compositeFb);
    final SystemHighlighter systemHighlighter = new SystemHighlighter(project, compositeFb);
    final DebugPanelModel model = new DebugPanelModel(counterexample);

    final DebugPanel debugPanel = new DebugPanel(counterexample, backtraceService, systemHighlighter);
    debugPanel.setModel(model);
    debugPanel.initView();
    debugPanel.initController();

    return debugPanel;
  }
}
