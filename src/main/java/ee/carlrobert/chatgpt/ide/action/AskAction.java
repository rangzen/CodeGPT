package ee.carlrobert.chatgpt.ide.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import ee.carlrobert.chatgpt.client.ClientFactory;
import ee.carlrobert.chatgpt.ide.toolwindow.ToolWindowService;
import org.jetbrains.annotations.NotNull;

public class AskAction extends AnAction {

  @Override
  public void update(@NotNull AnActionEvent event) {
    event.getPresentation().setEnabled(event.getProject() != null);
  }

  @Override
  public void actionPerformed(@NotNull AnActionEvent event) {
    var project = event.getProject();
    if (project != null) {
      var toolWindowService = project.getService(ToolWindowService.class);
      var toolWindow = toolWindowService.getToolWindow(project);
      new ClientFactory().getClient().clearPreviousSession();
      toolWindow.show();
      toolWindow.setTitle("");
      toolWindowService.removeAll();
      toolWindowService.paintLandingView();
    }
  }
}
