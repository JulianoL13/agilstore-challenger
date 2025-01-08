CREATE TABLE IF NOT EXISTS categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

INSERT INTO categories (name, description) VALUES
    ('Electronics', 'Devices and gadgets for everyday use'),
    ('Clothing', 'Apparel and accessories for all genders'),
    ('Home & Kitchen', 'Furniture, appliances, and decor for your home'),
    ('Sports', 'Sports equipment and outdoor gear'),
    ('Toys', 'Toys for children of all ages'),
    ('Books', 'Books for all ages and interests'),
    ('Beauty', 'Cosmetics and skincare products'),
    ('Health', 'Vitamins, supplements, and health products'),
    ('Food', 'Groceries and food items for all diets'),
    ('Pets', 'Pet food, toys, and accessories');

