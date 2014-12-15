package com.alesegdia.tilevisor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.alesegdia.map.Tilemap;
import com.alesegdia.map.generator.RandomMapGenerator;
import com.alesegdia.map.renderer.RectMapRenderer;

public class MyGdxGame extends ApplicationAdapter {
	
	private static final float rotationSpeed = 0;
	private static final float moveSpeed = 1f;
	private static final float camSize = 400;
	ShapeRenderer shaperenderer;
	RectMapRenderer rectmaprenderer;
	SpriteBatch batch;
	Texture img;
	Tilemap tilemap;
	Camera cam;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		cam = new OrthographicCamera( camSize, w / h );
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		cam.update();

		
		RandomMapGenerator randgen = new RandomMapGenerator();
		RandomMapGenerator.Config cfg = randgen.new Config();
		cfg.width = 10;
		cfg.height = 10;
		cfg.thres = 0.5f;
		tilemap = randgen.Generate( cfg );
		tilemap.Debug();
		shaperenderer = new ShapeRenderer();
		shaperenderer.setAutoShapeType(true);
		rectmaprenderer = new RectMapRenderer( tilemap );
	}

	@Override
	public void render () {
		handleCameraInput();
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		shaperenderer.setProjectionMatrix(cam.combined);

		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		batch.end();
		shaperenderer.begin(ShapeType.Filled);
		rectmaprenderer.Render( shaperenderer, cam.viewportWidth / 2.0f - rectmaprenderer.Height() / 2.0f, cam.viewportHeight / 2.0f - rectmaprenderer.Width() / 2.0f );
		shaperenderer.end();
	}
	
    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = camSize;
        cam.viewportHeight = camSize * height/width;
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);

        cam.update();
    }

	private void handleCameraInput() {

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cam.translate(-moveSpeed, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            cam.translate(moveSpeed, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            cam.translate(0, -moveSpeed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            cam.translate(0, moveSpeed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cam.rotate(-rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            cam.rotate(rotationSpeed, 0, 0, 1);
        }

	}
}
