from PIL import Image, ImageDraw

MAX_ITER = 160
ESCAPE = 2


def mandelbrot_escape(c):
    z = 0
    i = 0  # Current iteration
    while abs(z) < ESCAPE and i < MAX_ITER:
        z = z * z + c
        i = i + 1
    return i


def plot(xmin, xmax, ymin, ymax, scale):
    pixels_x = int((xmax - xmin) * scale)
    pixels_y = int((ymax - ymin) * scale)
    output = Image.new("HSV", (pixels_x, pixels_y), 0)
    draw = ImageDraw.Draw(output)
    for i in range(0, pixels_x):
        real_to_calculate = xmin + (xmax - xmin) * (i / pixels_x)
        for j in range(0, pixels_y):
            im_to_calculate = ymin + (ymax - ymin) * (j / pixels_y)
            c = complex(real_to_calculate, im_to_calculate)
            hue = mandelbrot_escape(c)
            draw.point((i, j), (hue, 255, 255))
    output.show()


# plot(-1.05, -0.95, 0.25, 0.4, 10000)
plot(-2, 1, -1, 1, 500)
