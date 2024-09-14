<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>DataScienceDashboard</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
  </head>
  <body>
    <div class="container mt-5">
      <h1 class="text-center">Data Science Dashboard</h1>
      <p class="text-center mt-3">
        This tool allows you to visualize your datasets and train simple machine
        learning models.
      </p>
      <p class="text-center mt-3">
        Upload your dataset (CSV file) to get started with data visualization
        and model training.
      </p>
      <div class="row justify-content-center mt-5">
        <div class="col-md-6">
          <form action="upload" method="post" enctype="multipart/form-data">
            <div class="form-group">
              <label for="file">Upload your CSV file</label>
              <input
                type="file"
                class="form-control"
                name="file"
                id="file"
                accept=".csv"
                required
              />
              <button type="submit" class="btn btn-primary">
                Upload and Start
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
