package dc.code.compensator;

import java.nio.file.Paths;

import org.eclipse.fx.code.editor.LocalSourceFileInput;
import org.eclipse.fx.code.editor.fx.services.internal.DefaultSourceViewerConfiguration;
import org.eclipse.fx.code.editor.services.InputDocument;
import org.eclipse.fx.core.event.EventBus;
import org.eclipse.fx.core.event.SimpleEventBus;
import org.eclipse.fx.text.ui.source.SourceViewer;
import org.eclipse.fx.text.ui.source.SourceViewerConfiguration;
import org.eclipse.jface.text.IDocumentExtension3;

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

		LocalSourceFileInput f = new LocalSourceFileInput(Paths.get("resources/dc/code/compensator/examples/Sample.java"),
				eventBus);
		InputDocument doc = new InputDocument(f, eventBus);

		SourceViewer viewer = new SourceViewer();
		JavaPartitioner partitioner = new JavaPartitioner();
		SourceViewerConfiguration configuration = new DefaultSourceViewerConfiguration(f,
				new JavaPresentationReconciler(), null, null, null, null, null);

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