package dc.code.compensator;

import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.fx.code.editor.Input;
import org.eclipse.fx.code.editor.LocalSourceFileInput;
import org.eclipse.fx.code.editor.fx.services.internal.DefaultSourceViewerConfiguration;
import org.eclipse.fx.code.editor.services.CompletionProposal;
import org.eclipse.fx.code.editor.services.InputDocument;
import org.eclipse.fx.code.editor.services.ProposalComputer;
import org.eclipse.fx.code.editor.services.ProposalComputerTypeProvider;
import org.eclipse.fx.core.event.EventBus;
import org.eclipse.fx.core.event.SimpleEventBus;
import org.eclipse.fx.text.ui.source.SourceViewer;
import org.eclipse.fx.text.ui.source.SourceViewerConfiguration;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

import dc.code.compensator.dart.DartPartitioner;
import dc.code.compensator.dart.DartPresentationReconciler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SourceViewerSample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		EventBus eventBus = new SimpleEventBus();

		LocalSourceFileInput input = new LocalSourceFileInput(Paths.get("resources/dc/code/compensator/examples/sample.dart"),
				eventBus);
		InputDocument doc = new InputDocument(input, eventBus);

		SourceViewer viewer = new SourceViewer();
		DartPartitioner partitioner = new DartPartitioner();
		DartPresentationReconciler reconciler = new DartPresentationReconciler();
		ProposalComputer propposalComputer = new ProposalComputer() {
			
			private CompletableFuture<List<ICompletionProposal>> future;
			
			@Override
			public CompletableFuture<List<CompletionProposal>> compute() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		SourceViewerConfiguration configuration = new DefaultSourceViewerConfiguration(null, input,
				reconciler, null, propposalComputer , null, null, null, null, null, null, null, null, null);

		doc.setDocumentPartitioner(IDocumentExtension3.DEFAULT_PARTITIONING, partitioner);
		doc.setDocumentPartitioner(partitioner);
		partitioner.connect(doc);

		viewer.configure(configuration);
		viewer.setDocument(doc, configuration.getAnnotationModel());

		Scene s = new Scene(new BorderPane(viewer.getTextWidget()));
		s.getStylesheets().add(getClass().getResource("css/dart-source.css").toExternalForm());
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}