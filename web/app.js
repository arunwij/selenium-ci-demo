import express from "express";
const app = express();
app.use(express.urlencoded({ extended: true }));
app.use(express.static("public"));

app.post("/submit", (req, res) => {
	const name = req.body.name || "friend";
	res.send(`
    <html><body style="font-family:system-ui">
      <h1 id="success">Thanks, ${name}! Form submitted.</h1>
      <a href="/">Back</a>
    </body></html>
  `);
});

app.listen(3000, () => console.log("Web running on http://localhost:3000"));
