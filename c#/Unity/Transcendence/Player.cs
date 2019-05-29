using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Player : MonoBehaviour {

    Rigidbody2D rb;
    public float movementSpeed = 1f;
    public float movementX = 0f;
    public float movementY = 0f;
    public bool isJumping = false;
    public bool isSpring = false;
    float count;



    // Use this for initialization
    void Start()
    {
        rb = GetComponent<Rigidbody2D>();
    }

    // Update is called once per frame
    void Update()
    {
        movementX = Input.GetAxis("Horizontal") * movementSpeed;
        movementY = Input.GetAxis("Vertical") * movementSpeed;


        if(Input.GetKeyDown(KeyCode.Escape))
        {
            SceneManager.LoadScene("Main Menu");
        }
        if(Input.GetAxis("Horizontal") < 0)
        {
            rb.GetComponent<SpriteRenderer>().flipX = true;
        }
        else if(Input.GetAxis("Horizontal") > 0)
        {
            rb.GetComponent<SpriteRenderer>().flipX = false;
        }
    }


    private void FixedUpdate()
    {
        if(!isSpring)
        {

        Vector2 velocity = rb.velocity;
        velocity.x = movementX;
        velocity.y = movementY;
        rb.velocity = velocity;
        }

    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
       if(collision.gameObject.tag == "spring" && count <200)
        {

            
                isSpring = true;
                count++;
                Debug.Log("aaaaaaaaaaaaaa");
                Vector2 velocity = rb.velocity;
                velocity.x = 30f;
                velocity.y = 0f;
                rb.velocity = velocity;
            }
        else {
                isSpring = false;
            }
        

    }
}
