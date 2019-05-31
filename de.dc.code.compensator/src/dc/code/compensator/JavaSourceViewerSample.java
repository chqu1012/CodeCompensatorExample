package dc.code.compensator;

import java.nio.file.Paths;

import org.eclipse.fx.code.editor.Input;
import org.eclipse.fx.code.editor.LocalSourceFileInput;
import org.eclipse.fx.code.editor.fx.services.CompletionProposalPresenter;
import org.eclipse.fx.code.editor.fx.services.internal.DefaultSourceViewerConfiguration;
import org.eclipse.fx.code.editor.services.BehaviorContributor;
import org.eclipse.fx.code.editor.services.EditorOpener;
import org.eclipse.fx.code.editor.services.HoverInformationProvider;
import org.eclipse.fx.code.editor.services.InputDocument;
import org.eclipse.fx.code.editor.services.NavigationProvider;
import org.eclipse.fx.code.editor.services.ProposalComputer;
import org.eclipse.fx.code.editor.services.SearchProvider;
import org.eclipse.fx.core.AppMemento;
import org.eclipse.fx.core.ThreadSynchronize;
import org.eclipse.fx.core.event.EventBus;
import org.eclipse.fx.core.event.SimpleEventBus;
import org.eclipse.fx.text.ui.contentassist.IContextInformationValidator;
import org.eclipse.fx.text.ui.presentation.PresentationReconciler;
import org.eclipse.fx.text.ui.source.AnnotationPresenter;
import org.eclipse.fx.text.ui.source.SourceViewer;
import org.eclipse.fx.text.ui.source.SourceViewerConfiguration;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.source.IAnnotationModel;

import dc.code.compensator.javalang.JavaPartitioner;
import dc.code.compensator.javalang.JavaPresentationReconciler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JavaSourceViewerSample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		EventBus eventBus = new SimpleEventBus();

		LocalSourceFileInput input = new LocalSourceFileInput(Paths.get("resources/dc/code/compensator/examples/Sample.java"),
				eventBus);
		InputDocument doc = new InputDocument(input, eventBus);

		SourceViewer viewer = new SourceViewer();
		JavaPartitioner partitioner = new JavaPartitioner();
		ThreadSynchronize threadSynchronize = null;
		PresentationReconciler reconciler = new JavaPresentationReconciler();
		AppMemento appMemento = null;
		ProposalComputer proposalComputer = null;
		IAnnotationModel annotationModel = null;
		AnnotationPresenter annotationPresenter = null;
		HoverInformationProvider hoverInformationProvider = null;
		CompletionProposalPresenter proposalPresenter = null;
		SearchProvider searchProvider = null;
		NavigationProvider navigationProvider = null;
		EditorOpener editorOpener = null;
		BehaviorContributor behaviorContributor = null;
		IContextInformationValidator contextInformationValidator = null;
		SourceViewerConfiguration configuration = new DefaultSourceViewerConfiguration(threadSynchronize, input,
				reconciler, appMemento, proposalComputer, annotationModel, annotationPresenter,
				hoverInformationProvider, proposalPresenter, searchProvider, navigationProvider, editorOpener,
				behaviorContributor, contextInformationValidator);

		doc.setDocumentPartitioner(IDocumentExtension3.DEFAULT_PARTITIONING, partitioner);
		doc.setDocumentPartitioner(partitioner);
		partitioner.connect(doc);

		viewer.configure(configuration);
		viewer.setDocument(doc, configuration.getAnnotationModel());

		Scene s = new Scene(new BorderPane(viewer.getTextWidget()));
		s.getStylesheets().add(getClass().getResource("css/java-source.css").toExternalForm());
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}