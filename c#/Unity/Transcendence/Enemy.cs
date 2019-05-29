using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Enemy : MonoBehaviour {

    Rigidbody2D rb1;
    GameObject player;
    private bool isLeft;
    private bool isRight;
    private float xDifference;
    private float yDifference;
    private float speed = 2.1f;
    private float movement;
    private float unit;

	// Use this for initialization
	void Start () {
        rb1 = GetComponent<Rigidbody2D>();
        player = GameObject.FindGameObjectWithTag("Player1");
	}
	
	// Update is called once per frame
	void Update () {
        Vector2 velocity = rb1.velocity;
        xDifference = player.transform.position.x - rb1.transform.position.x;
        yDifference = player.transform.position.y - rb1.transform.position.y;

        unit = Mathf.Sqrt((xDifference * xDifference) + (yDifference * yDifference));
        if(xDifference > 0 && !isLeft)
        {
            GetComponent<SpriteRenderer>().flipX = false;

        }
        else if(xDifference < 0)
        {
            GetComponent<SpriteRenderer>().flipX = true;

        }
        {

        }
        velocity.x = xDifference / unit * speed;
        velocity.y = yDifference / unit * speed;

        rb1.velocity = velocity;

    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        Debug.Log("Enemy Collision");
        if(collision.gameObject.tag == "Player1")
        {

            collision.gameObject.GetComponent<CharacterHealth>().TakeDamage(1);
        }
    }


}
